package com.qa.user_app.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.user_app.data.entity.Pokemon;
import com.qa.user_app.data.repository.PokemonRepository;

// Testing service integration

@SpringBootTest
@Transactional
public class PokemonServiceIntegrationTest {

	@Autowired
	private PokemonService pokeService;
	
	@Autowired
	private PokemonRepository pokeRepo;
	
	
	private List<Pokemon> pokemonInDb;
	private long nextPokemonId;
	
	// Initialise in database before each test
	@BeforeEach
	public void init() {
		List<Pokemon> pokemon = List.of(new Pokemon(1L, 1, "Bulbasaur", true), new Pokemon(2L, 2, "Ivysaur", true),
				new Pokemon(3L, 3, "Venusaur", false));
		pokemonInDb = new ArrayList<>();
		pokemonInDb.addAll(pokeRepo.saveAll(pokemon));
		int dbSize = pokemonInDb.size();
		// To determine what the id of the next Pokemon added to the database would be
		// - we take the size and minus one (- 1) to get to the last element in the ArrayList
		// - then add one (+ 1) to the last element's id to determine the next id
		nextPokemonId = pokemonInDb.get(dbSize - 1).getId() + 1;
	}
	
	// READ all test
	@Test
	public void getAllPokemonTest() {
		assertThat(pokemonInDb).isEqualTo(pokeService.getAll());
	}
	
	// READ by id test
	@Test
	public void getPokemonByIdTest() {
		Pokemon findPokemon = pokemonInDb.get(0);
		assertThat(pokeService.getById(findPokemon.getId())).isEqualTo(findPokemon);
	}
	
	
	// CREATE test
	@Test
	public void createPokemonTest() {
		Pokemon newPokemon = new Pokemon(4, "Charmander", true);
		Pokemon createdPokemon = new Pokemon
						(nextPokemonId, 
						newPokemon.getPokedexNumber(), 
						newPokemon.getName(), 
						newPokemon.getCanEvolve());
		
		assertThat(createdPokemon).isEqualTo(pokeService.create(newPokemon));
	}
	
	// UPDATE test
	@Test
	public void updatePokemonTest() {
		Pokemon oldPokemon = pokemonInDb.get(0);
		Pokemon newPokemon = new Pokemon
						(oldPokemon.getPokedexNumber(), 
						"Pikachu", 
						oldPokemon.getCanEvolve());
		Pokemon updatedPokemon = pokeService.update(oldPokemon.getId(), newPokemon);
		assertThat(oldPokemon).isEqualTo(updatedPokemon);
	}
	
	@Test
	public void deletePokemonTest() {
		Pokemon pokemonToDelete = pokemonInDb.get(0);
		pokeService.delete(pokemonToDelete.getId());
		assertThat(pokeRepo.findById(pokemonToDelete.getId())).isEqualTo(Optional.empty());
	}
	
	
	
}
