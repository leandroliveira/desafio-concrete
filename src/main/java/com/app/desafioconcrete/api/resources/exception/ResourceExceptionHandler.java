package com.app.desafioconcrete.api.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.desafioconcrete.api.exceptions.ExcecaoUsuarioInvalido;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ExcecaoUsuarioInvalido.class)
	public ResponseEntity<StandardError> objectNotFound(ExcecaoUsuarioInvalido e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
	
	
}
