package com.app.desafioconcrete.api.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.desafioconcrete.api.dto.LoginDTO;
import com.app.desafioconcrete.api.dto.ProfileDTO;
import com.app.desafioconcrete.api.dto.UserDTO;
import com.app.desafioconcrete.api.entities.User;
import com.app.desafioconcrete.api.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
    UserService userService;
	
	@PostMapping("/perfilUsuario")
    public ResponseEntity<UserDTO> profile(@RequestBody ProfileDTO profileDTO) {

        UserDTO userDTO = userService.getProfile(profileDTO);

        return ResponseEntity.ok().body(userDTO);
    }
	
	@PostMapping("/cadastro")
    public ResponseEntity<?> cadastro(@RequestBody User user) {

        UserDTO userDTO = userService.createUser(user);

        return ResponseEntity.ok().body(userDTO);
    }
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login (@RequestBody LoginDTO pLoginDTO) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		UserDTO userDTO = userService.loginUser(pLoginDTO);

		return ResponseEntity.ok().body(userDTO);
	}
	
	
}
