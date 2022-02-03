package com.qa.user_app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.user_app.data.entity.Pokemon;
import com.qa.user_app.service.PokemonService;

// Start application with only beans for the controller layer
@WebMvcTest(PokemonController.class)
public class PokemonControllerWebIntegrationTest implements IControllerWebIntegrationTest{
	
	@Autowired
	private PokemonController pokeController;
	
	// mocked pokemon service
	@MockBean
	private PokemonService pokeService;
	
	private List<Pokemon> pokemon;
	private Pokemon pokemonWithoutId;
	private Pokemon pokemonWithId;
	private Pokemon foundPokemon;
	private Pokemon updatedPokemon;
	
	@BeforeEach
	public void init() {
		pokemon = new ArrayList<>();
		pokemon.addAll(List.of(new Pokemon(1L, 1, "Bulbasaur", true), new Pokemon(2L, 2, "Ivysaur", true),
				new Pokemon(3L, 3, "Venusaur", false)));
		foundPokemon = new Pokemon(1L, 1, "Bulbasaur", true);
		int listSize = pokemon.size();
		long nextPokemonId = pokemon.get(listSize - 1).getId() + 1;
		pokemonWithoutId = new Pokemon(6, "Charizard", true);
		updatedPokemon = new Pokemon(1L, 6, "Charizard", true);
		pokemonWithId = new Pokemon(nextPokemonId, 6, "Charizard", true);
	}
	
	// READ all test
	@Test
	public void getAllTest() {
		// Given
		ResponseEntity<List<Pokemon>> expected = new ResponseEntity<List<Pokemon>>(pokemon, HttpStatus.OK);
		
		// When
		when(pokeService.getAll()).thenReturn(pokemon);
		
		// Then
		ResponseEntity<List<Pokemon>> actual = pokeController.getAll();
		assertThat(expected).isEqualTo(actual);
		
		// Verify
		verify(pokeService).getAll();
	}
	
	// READ by id test
	@Test
	public void getById() {
		ResponseEntity<Pokemon> expected = ResponseEntity.of(Optional.of(foundPokemon));
		
		when(pokeService.getById(foundPokemon.getId())).thenReturn(foundPokemon);
		
		ResponseEntity<Pokemon> actual = pokeController.getById(foundPokemon.getId());
		
		assertThat(expected).isEqualTo(actual);
		
		verify(pokeService).getById(foundPokemon.getId());
	}
	
	// CREATE test
	@Test
	public void createTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/pokemon/" + String.valueOf(pokemonWithId.getId()));
		
		ResponseEntity<Pokemon> expected = new ResponseEntity<Pokemon>
		// body 	httpHeaders		responseStatusCode
		(pokemonWithId, headers, HttpStatus.CREATED);
		
		when(pokeService.create(pokemonWithoutId)).thenReturn(pokemonWithId);
		
		ResponseEntity<Pokemon> actual = pokeController.create(pokemonWithoutId);
		assertThat(expected).isEqualTo(actual);
		
		verify(pokeService).create(pokemonWithoutId);
	}
	
	// UPDATE test
	@Test
	public void updateTest() {
		ResponseEntity<Pokemon> expected = new ResponseEntity<Pokemon>(updatedPokemon, HttpStatus.ACCEPTED);
		
		when(pokeService.update(foundPokemon.getId(), pokemonWithoutId)).thenReturn(updatedPokemon);
		
		ResponseEntity<Pokemon> actual = pokeController.update(foundPokemon.getId(), pokemonWithoutId);
		
		assertThat(expected).isEqualTo(actual);
		verify(pokeService).update(foundPokemon.getId(), pokemonWithoutId);
	}
	
	// DELETE test
	@Test
	public void deleteTest() {
		ResponseEntity<?> expected = ResponseEntity.accepted().build();
		ResponseEntity<?> actual = pokeController.delete(foundPokemon.getId());
		
		assertThat(expected).isEqualTo(actual);
		
		verify(pokeService).delete(foundPokemon.getId());
	}

}
