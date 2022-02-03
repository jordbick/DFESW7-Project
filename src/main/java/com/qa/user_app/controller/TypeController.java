package com.qa.user_app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.user_app.data.entity.Type;
import com.qa.user_app.service.TypeService;

@RestController
@RequestMapping(path = "/type")
public class TypeController implements Controller<Type> {

	private TypeService typeService;

	@Autowired
	public TypeController(TypeService typeService) {
		this.typeService = typeService;
	}

	// READ ALL
	@Override
	@GetMapping
	public ResponseEntity<List<Type>> getAll() {
		return ResponseEntity.ok(typeService.getAll());
	}

	// READ BY ID
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Type> getById(@PathVariable("id") Long id) {
		Type pokeInDb = typeService.getById(id);
		ResponseEntity<Type> response = ResponseEntity.status(HttpStatus.OK).body(pokeInDb);
		return response;
	}

	// CREATE
	@Override
	@PostMapping
	public ResponseEntity<Type> create(@Valid @RequestBody Type type) {
		Type createdType = typeService.create(type);
		HttpHeaders header = new HttpHeaders();
		header.add("Location", "/type/" + String.valueOf(createdType.getId()));

		ResponseEntity<Type> response = new ResponseEntity<Type>(createdType, // body
				header, // Http Header
				HttpStatus.CREATED); // Response status
		return response;
	}

	// UPDATE
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<Type> update(@PathVariable("id") Long id, @Valid @RequestBody Type type) {
		Type updatedType = typeService.update(id, type);
		ResponseEntity<Type> response = ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedType);
		return response;
	}

	// DELETE
	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		typeService.delete(id);

		return ResponseEntity.accepted().build();
	}

}
