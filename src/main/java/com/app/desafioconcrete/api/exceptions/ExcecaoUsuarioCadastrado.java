package com.app.desafioconcrete.api.exceptions;

import org.springframework.http.HttpStatus;

public class ExcecaoUsuarioCadastrado extends RuntimeException{
	
	public ExcecaoUsuarioCadastrado(HttpStatus httpStatus) {
		super("Usuário já existe no sistema.");
	}
}
