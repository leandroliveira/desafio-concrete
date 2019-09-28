package com.app.desafioconcrete.api.exceptions;

import org.springframework.http.HttpStatus;

public class ExcecaoTokenInexistente extends RuntimeException{
	
	public ExcecaoTokenInexistente(HttpStatus httpStatus) {
		super("NÃO AUTORIZADO.");
	}
}
