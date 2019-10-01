package com.app.desafioconcrete.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.ConstraintViolationException;

import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.desafioconcrete.api.dto.LoginDTO;
import com.app.desafioconcrete.api.dto.ProfileDTO;
import com.app.desafioconcrete.api.dto.UserDTO;
import com.app.desafioconcrete.api.entities.Phone;
import com.app.desafioconcrete.api.entities.User;
import com.app.desafioconcrete.api.exceptions.ExcecaoSessaoInvalida;
import com.app.desafioconcrete.api.exceptions.ExcecaoTokenInexistente;
import com.app.desafioconcrete.api.exceptions.ExcecaoUsuarioCadastrado;
import com.app.desafioconcrete.api.exceptions.ExcecaoUsuarioInvalido;
import com.app.desafioconcrete.api.repository.PhoneRepository;
import com.app.desafioconcrete.api.repository.UserRepository;
import com.app.desafioconcrete.api.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@Override
	public UserDTO createUser(User pUser){
		UserDTO userDTO = new UserDTO();
		try {
			User user = userRepository.findByEmail(pUser.getEmail());
			
			if(user != null) {
				throw new ExcecaoUsuarioCadastrado();
			}
			
			
			
			pUser.setCriated(LocalDate.now());
			pUser.setModified(LocalDateTime.now());
			pUser.setLast_login(LocalDateTime.now());
		 	pUser.generateToken();
		 	//So criptografa o password se vier preenchido
		 	if(!pUser.getPassword().equals("")) {
		 		pUser.setPassword(this.getEncrypted(pUser.getPassword()));
		 	}
			
			user = userRepository.save(pUser);
	
			//linkamos os telefones com os usuarios 
			if (pUser.getPhones() != null && !pUser.getPhones().isEmpty()) {
				for (Phone phone : pUser.getPhones()) {
						phone.setUser(user);
				}
				phoneRepository.saveAll(pUser.getPhones());
			}
			
			BeanUtils.copyProperties(user, userDTO);
			return userDTO;
		} catch (RuntimeException e) {
			//O erro de constraint vem aninhado dentro de um RuntimeException
			Throwable throwable = ExceptionUtils.unwrapInvocationTargetException(e).getCause().getCause();
			if(throwable instanceof ConstraintViolationException)
				throw new ConstraintViolationException(((ConstraintViolationException) throwable).getConstraintViolations());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return userDTO;
	}

	@Override
	public UserDTO loginUser(LoginDTO pLoginDTO) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ExcecaoUsuarioInvalido {

		User user = this.userRepository.findByEmail(pLoginDTO.getEmail());
		if(user == null) {
			//Excecao mapeada no ResourceExceptionHandler para devolver o cod 401
			throw new ExcecaoUsuarioInvalido();
		}
		
		//Caso a senha esteja diferente do banco de dados
		if(!this.getEncrypted(pLoginDTO.getPassword()).equals(user.getPassword())){
			//Excecao mapeada no ResourceExceptionHandler para devolver o cod 401
			throw new ExcecaoUsuarioInvalido();
		}
		
		user.setLast_login(LocalDateTime.now());

		this.userRepository.save(user);

		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);

		return userDTO;
	}

	@Override
	public UserDTO getProfile(ProfileDTO pProfileDTO) throws ExcecaoTokenInexistente, ExcecaoSessaoInvalida{
		
		User userByToken = this.userRepository.findByToken(UUID.fromString(pProfileDTO.getToken()));
		if(userByToken == null) {
			throw new ExcecaoTokenInexistente();
		}
		
		User userById = this.userRepository.findById(UUID.fromString(pProfileDTO.getId()));
		if(userById != null) {
			if(userById.getToken().compareTo(userByToken.getToken()) != 0) {
				//Caso o token do usuario seja diferente do passado como parametro
				throw new ExcecaoTokenInexistente();
			} else if(LocalDateTime.now().minusMinutes(30).compareTo(userById.getLast_login()) > 0) {
				//Caso a sessao tenha estourado o tempo
				throw new ExcecaoSessaoInvalida();
			}
		} else {
			throw new ExcecaoTokenInexistente();
		}
		
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userById, userDTO);
		return userDTO;
	}

	//Encripta a senha passada como parametro no formato SHA-256
	@Override
	public String getEncrypted(String pPassword) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException  {

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(pPassword.getBytes());
		
		return UUID.nameUUIDFromBytes(md.digest()).toString();
		
	}
	
}
