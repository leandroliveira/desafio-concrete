package com.app.desafioconcrete.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public UserDTO createUser(User pUser) {
		UserDTO userDTO = new UserDTO();
		try {
			User user = userRepository.findByEmail(pUser.getEmail());
			
			if(user != null) {
				throw new ExcecaoUsuarioCadastrado(HttpStatus.UNAUTHORIZED);
			}
			
			pUser.setDtUserCriated(LocalDate.now());
			pUser.setDtCreationModified(LocalDateTime.now());
			pUser.setLastUserLogin(LocalDateTime.now());
		 	pUser.generateToken();
			pUser.setPassword(this.getEncrypted(pUser.getPassword()));
			
			user = userRepository.save(pUser);
	
			//linkamos os telefones com os usuarios 
			if (pUser.getPhones() != null && !pUser.getPhones().isEmpty()) {
				for (Phone phone : pUser.getPhones()) {
						phone.setUser(user);
				}
				phoneRepository.saveAll(pUser.getPhones());
			}
			
			userDTO.setId(pUser.getId());
			userDTO.setName(pUser.getName());
			userDTO.setEmail(pUser.getEmail());
			userDTO.setDtUserCriated(LocalDate.now());
			userDTO.setDtCreationModified(LocalDateTime.now());
			userDTO.setLastUserLogin(LocalDateTime.now());
			userDTO.setToken(UUID.fromString(pUser.getPassword()));
			
			if(pUser.getPhones() != null && !pUser.getPhones().isEmpty()) {
				userDTO.setArrPhones(pUser.getPhones());
			}
			return userDTO;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userDTO;
	}

	@Override
	public UserDTO loginUser(LoginDTO pLoginDTO) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		User user = this.userRepository.findByEmail(pLoginDTO.getEmail());
		if(user == null) {
			//Excecao mapeada no ResourceExceptionHandler para devolver o cod 401
			throw new ExcecaoUsuarioInvalido();
		}
		
		//Caso a senha esteja diferente do banco de dados
		if(!this.getEncrypted(pLoginDTO.getPassword()).equalsIgnoreCase(user.getPassword())){
			//Excecao mapeada no ResourceExceptionHandler para devolver o cod 401
			throw new ExcecaoUsuarioInvalido();
		}
		
		user.setLastUserLogin(LocalDateTime.now());
		user.generateToken();

		this.userRepository.save(user);

		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);

		return userDTO;
	}

	@Override
	public UserDTO getProfile(ProfileDTO pProfileDTO) {
		User userByToken = this.userRepository.findByUserToken(UUID.fromString(pProfileDTO.getToken()));
		if(userByToken == null) {
			throw new ExcecaoTokenInexistente(HttpStatus.UNAUTHORIZED);
		}
		
		User userById = this.userRepository.findById(UUID.fromString(pProfileDTO.getId()));
		if(userById != null) {
			if(userById.getUserToken().compareTo(userById.getUserToken()) != 0) {
				//Caso o token do usuario seja diferente do passado como parametro
				throw new ExcecaoTokenInexistente(HttpStatus.UNAUTHORIZED);
			} else if(LocalDateTime.now().minusMinutes(30).compareTo(userById.getLastUserLogin()) > 0) {
				//Caso a sessao tenha estourado o tempo
				throw new ExcecaoSessaoInvalida(HttpStatus.GONE);
			}
		} else {
			throw new ExcecaoTokenInexistente(HttpStatus.UNAUTHORIZED);
		}
		
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userById, userDTO);
		return userDTO;
	}

	//Encripta a senha passada como parametro no formato SHA-256
	public String getEncrypted(String pPassword) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException  {

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(pPassword.getBytes());
		
		return UUID.nameUUIDFromBytes(md.digest()).toString();
		
	}
	
}
