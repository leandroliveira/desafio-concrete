package com.app.desafioconcrete.api.exceptions;

public class ExcecaoUsuarioInvalido extends RuntimeException{
	private static final long serialVersionUID = 1L;

	private static final String msg = "Usu�rio/Senha inv�lidos.";

	public ExcecaoUsuarioInvalido () {
		super(msg);
	}

	public ExcecaoUsuarioInvalido(String msg, Throwable cause) {
		super(msg, cause);
	}
}
