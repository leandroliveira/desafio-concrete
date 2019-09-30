package com.app.desafioconcrete.api.exceptions;

public class ExcecaoTokenInexistente extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private static final String msg = "NÃO AUTORIZADO.";

	public ExcecaoTokenInexistente () {
		super(msg);
	}

	public ExcecaoTokenInexistente(String msg, Throwable cause) {
		super(msg, cause);
	}
}
