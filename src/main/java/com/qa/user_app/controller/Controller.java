package com.qa.user_app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface Controller<T> {

	//READ ALL
	public ResponseEntity<List<T>> getAll();
	
	//READ BY ID
	public ResponseEntity<T> getById(Long id);
	
	//CREATE
	public ResponseEntity<T> create(T item);
	
	//UPDATE
	public ResponseEntity<T> update(Long id, T item);
	
	//DELETE
	public ResponseEntity<?> delete(Long id);
	
}
