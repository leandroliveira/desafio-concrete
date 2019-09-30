package com.app.desafioconcrete.api.exceptions;

public class ExcecaoUsuarioCadastrado extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private static final String msg = "E-mail já existente.";

	public ExcecaoUsuarioCadastrado () {
		super(msg);
	}

	public ExcecaoUsuarioCadastrado(String msg, Throwable cause) {
		super(msg, cause);
	}
}
