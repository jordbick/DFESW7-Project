package com.qa.user_app.exceptions;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(value = { ItemNotFoundException.class })
	public ResponseEntity<String> itemNotFoundExceptions(UserPrincipalNotFoundException unfe){
		return new ResponseEntity<String>(unfe.getMessage(), HttpStatus.NOT_FOUND);
	}
}
