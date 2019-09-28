package com.app.desafioconcrete.api.exceptions;

import org.springframework.http.HttpStatus;

public class ExcecaoUsuarioInvalido extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	/*
	 * public ExcecaoUsuarioInvalido() { super("Usuário/Senha inválido!."); }
	 */
	
	private static final String msg = "Usuário/Senha inválido!.";

	public ExcecaoUsuarioInvalido () {
		super(msg);
	}

	public ExcecaoUsuarioInvalido(String msg, Throwable cause) {
		super(msg, cause);
	}
}
