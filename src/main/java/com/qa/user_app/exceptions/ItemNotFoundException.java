package com.qa.user_app.exceptions;

import javax.persistence.EntityNotFoundException;

public class ItemNotFoundException extends EntityNotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ItemNotFoundException() {
		super();
	}

	public ItemNotFoundException(String message) {
		super(message);
	}

}
