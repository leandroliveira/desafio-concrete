package com.app.desafioconcrete.api.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.desafioconcrete.api.dto.LoginDTO;
import com.app.desafioconcrete.api.dto.ProfileDTO;
import com.app.desafioconcrete.api.dto.UserDTO;
import com.app.desafioconcrete.api.entities.Phone;
import com.app.desafioconcrete.api.entities.User;
import com.app.desafioconcrete.api.exception.ExcecaoUsuarioCadastrado;
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
		
		User user = userRepository.findByEmail(pUser.getEmail());
		
		if(user != null) {
			throw new ExcecaoUsuarioCadastrado(HttpStatus.UNAUTHORIZED);
		}
		
		pUser.setDtUserCriated(LocalDate.now());
		pUser.setDtCreationModified(LocalDateTime.now());
		pUser.setLastUserLogin(LocalDateTime.now());
	 	pUser.generateToken();

		user = userRepository.save(pUser);

		//linkamos os telefones com os usuarios 
		if (pUser.getArrPhones() != null && !pUser.getArrPhones().isEmpty()) {
			for (Phone phone : pUser.getArrPhones()) {
					phone.setUser(user);
			}
			phoneRepository.saveAll(pUser.getArrPhones());
		}

		UserDTO userDTO = new UserDTO();
		userDTO.setName(pUser.getName());
		userDTO.setEmail(pUser.getEmail());
		userDTO.setDtUserCriated(LocalDate.now());
		userDTO.setDtCreationModified(LocalDateTime.now());
		userDTO.setLastUserLogin(LocalDateTime.now());
		
		if(pUser.getArrPhones() != null && !pUser.getArrPhones().isEmpty()) {
			userDTO.setArrPhones(pUser.getArrPhones());
		}
		
		return userDTO;
	}

	@Override
	public UserDTO loginUser(LoginDTO pLoginDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getProfile(ProfileDTO pProfileDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
