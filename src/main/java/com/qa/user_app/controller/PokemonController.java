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

import com.qa.user_app.data.entity.Pokemon;
import com.qa.user_app.service.PokemonService;

@RestController
@RequestMapping(path = "/pokemon")
public class PokemonController implements Controller<Pokemon>{

	private PokemonService pokemonService;

	@Autowired
	public PokemonController(PokemonService service) {
		super();
		this.pokemonService = service;
	}

	// READ ALL
	@Override
	@GetMapping
	public ResponseEntity<List<Pokemon>> getAll() {
		return ResponseEntity.ok(pokemonService.getAll());
	}

	// READ BY ID
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Pokemon> getById(@PathVariable("id") Long id) {
		Pokemon pokeInDb = pokemonService.getById(id);
		ResponseEntity<Pokemon> response = ResponseEntity.status(HttpStatus.OK).body(pokeInDb);
		return response;
	}

	// TODO other read methods

	// CREATE
	@Override
	@PostMapping
	public ResponseEntity<Pokemon> create(@Valid @RequestBody Pokemon pokemon) {
		Pokemon createdPokemon = pokemonService.create(pokemon);
		HttpHeaders header = new HttpHeaders();
		header.add("Location", "/pokemon/" + String.valueOf(createdPokemon.getId()));

		ResponseEntity<Pokemon> response = new ResponseEntity<Pokemon>
		(createdPokemon, // body
				header, // Http Header
				HttpStatus.CREATED); // Response status
		return response;
		
		// TODO should throw exception if pokedex number outside of range
	}
	
	// UPDATE
	@Override
	@PutMapping("/{id}")
	public ResponseEntity<Pokemon> update(@PathVariable("id") Long id, @Valid @RequestBody Pokemon pokemon){
		Pokemon updatedPokemon = pokemonService.update(id, pokemon);
		ResponseEntity<Pokemon> response = ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedPokemon);
		return response;
		
		//TODO should throw exception if pokedex vaues out of range
		// Exception if id does not exist
	}

	// DELETE
	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		pokemonService.delete(id);
		
		return ResponseEntity.accepted().build();
	}
	

}
