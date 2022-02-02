package com.qa.user_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.user_app.data.entity.Pokemon;
import com.qa.user_app.data.repository.PokemonRepository;
import com.qa.user_app.exceptions.ItemNotFoundException;
import com.qa.user_app.exceptions.PokemonAlreadyExists;

// Service communicates with repository

@Service
public class PokemonService implements IService<Pokemon> {

	// import data from repo
	private PokemonRepository pokemonRepo;

	@Autowired // dependency injection by using the constructor
	public PokemonService(PokemonRepository pokemonRepo) {
		this.pokemonRepo = pokemonRepo;
	}

	// READ ALL
	@Override
	public List<Pokemon> getAll() {
		return pokemonRepo.findAll();
	}

	// READ
	@Override
	public Pokemon getById(Long id) {
		if (pokemonRepo.existsById(id)) {
			return pokemonRepo.findById(id).get();
		} else {
			throw new ItemNotFoundException("Pokemon with id " + id + " does not exist");
		}
	}

	// CREATE
	@Override
	public Pokemon create(Pokemon pokemon) {
		if(!pokemonRepo.existsByName(pokemon.getName())) {
		return pokemonRepo.save(pokemon);
		} else {
			throw new PokemonAlreadyExists("Pokemon with name " + pokemon.getName() + " already exists");
		}
	}

	@Override
	public Pokemon update(Long id, Pokemon pokemon) {
		if (pokemonRepo.existsById(id)) {
			Pokemon updatedPokemon = pokemonRepo.getById(id);
			updatedPokemon.setPokedexNumber(pokemon.getPokedexNumber());
			updatedPokemon.setName(pokemon.getName());
			updatedPokemon.setCanEvolve(pokemon.getCanEvolve());
			updatedPokemon.setType(pokemon.getType());
			return pokemonRepo.save(updatedPokemon);
		} else {
			throw new ItemNotFoundException("Pokemon with id " + id + " does not exist");
		}
		
		// TODO throw Exception
		
	}

	@Override
	public void delete(Long id) {
		
		if (pokemonRepo.existsById(id)) {
			pokemonRepo.deleteById(id);
		} else {
			throw new ItemNotFoundException("Pokemon with id " + id + " does not exist");
		}

	}

}
