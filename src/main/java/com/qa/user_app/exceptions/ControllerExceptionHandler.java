package com.qa.user_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	
	// Handles Pokemon/Type not found exception
	@ExceptionHandler(value = { ItemNotFoundException.class })
	public ResponseEntity<String> itemNotFoundExceptions(ItemNotFoundException infe){
		return new ResponseEntity<String>(infe.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	// Handles pokemon name already exists exception
	@ExceptionHandler(value = { PokemonAlreadyExists.class })
	public ResponseEntity<String> pokemonAlreadyExists(PokemonAlreadyExists poe){
		return new ResponseEntity<String>(poe.getMessage(), HttpStatus.CONFLICT);
	}
}


