package com.app.desafioconcrete.api.exceptions;

public class ExcecaoSessaoInvalida extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	private static final String msg = "Sess�o inv�lida.";

	public ExcecaoSessaoInvalida () {
		super(msg);
	}

	public ExcecaoSessaoInvalida(String msg, Throwable cause) {
		super(msg, cause);
	}
}
