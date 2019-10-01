package com.app.desafioconcrete.api.resources.exception;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.desafioconcrete.api.exceptions.ExcecaoSessaoInvalida;
import com.app.desafioconcrete.api.exceptions.ExcecaoTokenInexistente;
import com.app.desafioconcrete.api.exceptions.ExcecaoUsuarioCadastrado;
import com.app.desafioconcrete.api.exceptions.ExcecaoUsuarioInvalido;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ExcecaoUsuarioInvalido.class)
	public ResponseEntity<StandardError> usuarioInvalido(ExcecaoUsuarioInvalido e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
	
	@ExceptionHandler(ExcecaoUsuarioCadastrado.class)
	public ResponseEntity<StandardError> usuarioCadastrado(ExcecaoUsuarioCadastrado e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
	
	@ExceptionHandler(ExcecaoTokenInexistente.class)
	public ResponseEntity<StandardError> tokenInexistente(ExcecaoTokenInexistente e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
	
	@ExceptionHandler(ExcecaoSessaoInvalida.class)
	public ResponseEntity<StandardError> sessaoInvalida(ExcecaoSessaoInvalida e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.GONE.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.GONE).body(err);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> constraintViolation(ConstraintViolationException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
}
