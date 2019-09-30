package com.app.desafioconcrete.api.exceptions;

public class ExcecaoUsuarioInvalido extends RuntimeException{
	private static final long serialVersionUID = 1L;

	private static final String msg = "Usuário/Senha inválidos.";

	public ExcecaoUsuarioInvalido () {
		super(msg);
	}

	public ExcecaoUsuarioInvalido(String msg, Throwable cause) {
		super(msg, cause);
	}
}
