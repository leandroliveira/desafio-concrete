package com.app.desafioconcrete.api.exceptions;

import org.springframework.http.HttpStatus;

public class ExcecaoUsuarioCadastrado extends RuntimeException{
	
	public ExcecaoUsuarioCadastrado(HttpStatus httpStatus) {
		super("Usu�rio j� existe no sistema.");
	}
}
