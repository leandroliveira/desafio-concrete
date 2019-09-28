package com.app.desafioconcrete.api.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.app.desafioconcrete.api.dto.LoginDTO;
import com.app.desafioconcrete.api.dto.ProfileDTO;
import com.app.desafioconcrete.api.dto.UserDTO;
import com.app.desafioconcrete.api.entities.User;

public interface UserService {

	UserDTO createUser(User user);
	
	UserDTO loginUser(LoginDTO pLoginDTO) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	
	UserDTO getProfile(ProfileDTO pProfileDTO);
	
}
