package com.app.desafioconcrete.api.exception;

import org.springframework.http.HttpStatus;

public class ExcecaoUsuarioCadastrado extends RuntimeException{
	
	public ExcecaoUsuarioCadastrado(HttpStatus httpStatus) {
		super("Usu�rio j� existe no sistema.");
	}
}
