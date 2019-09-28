package com.app.desafioconcrete.api.exceptions;

import org.springframework.http.HttpStatus;

public class ExcecaoUsuarioInvalido extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	/*
	 * public ExcecaoUsuarioInvalido() { super("Usu�rio/Senha inv�lido!."); }
	 */
	
	private static final String msg = "Usu�rio/Senha inv�lido!.";

	public ExcecaoUsuarioInvalido () {
		super(msg);
	}

	public ExcecaoUsuarioInvalido(String msg, Throwable cause) {
		super(msg, cause);
	}
}
