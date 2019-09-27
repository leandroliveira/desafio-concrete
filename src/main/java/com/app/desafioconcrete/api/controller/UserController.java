package com.app.desafioconcrete.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.desafioconcrete.api.dto.LoginDTO;
import com.app.desafioconcrete.api.dto.UserDTO;
import com.app.desafioconcrete.api.entities.User;
import com.app.desafioconcrete.api.service.UserService;

@RequestMapping
@RestController
public class UserController {

	@Autowired
    UserService userService;
	
	@PostMapping("/cadastro")
    public ResponseEntity<?> cadastro(@RequestBody User user) {

        UserDTO userDTO = userService.createUser(user);

        return ResponseEntity.ok().body(userDTO);
    }
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login (@RequestBody LoginDTO pLoginDTO) {

		UserDTO userDTO = userService.loginUser(pLoginDTO);

		return ResponseEntity.ok().body(userDTO);
	}
	
	
}
