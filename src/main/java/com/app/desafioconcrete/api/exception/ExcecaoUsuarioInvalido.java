package com.app.desafioconcrete.api.exception;

import org.springframework.http.HttpStatus;

public class ExcecaoUsuarioInvalido extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public ExcecaoUsuarioInvalido(HttpStatus httpStatus) {
		super("Usuário/Senha inválido!.");
	}
}
