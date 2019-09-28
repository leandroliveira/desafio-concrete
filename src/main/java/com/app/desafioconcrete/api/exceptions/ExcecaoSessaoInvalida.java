package com.app.desafioconcrete.api.exceptions;

import org.springframework.http.HttpStatus;

public class ExcecaoSessaoInvalida extends RuntimeException{
	
	public ExcecaoSessaoInvalida(HttpStatus httpStatus) {
		super("SESSÃO INVÁLIDA!.");
	}
}
