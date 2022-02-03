package com.qa.user_app.exceptions;

import javax.persistence.EntityExistsException;

public class PokemonAlreadyExists extends EntityExistsException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public PokemonAlreadyExists(String message) {
		super(message);
	}
	
	
}
