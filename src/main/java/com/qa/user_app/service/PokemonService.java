package com.qa.user_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.user_app.data.entity.Pokemon;
import com.qa.user_app.data.repository.PokemonRepository;

@Service
public class PokemonService implements IService<Pokemon> {

	// import data from repo
	private PokemonRepository pokemonRepo;

	@Autowired // dependency injection by using the constructor
	public PokemonService(PokemonRepository pokemonRepo) {
		this.pokemonRepo = pokemonRepo;
	}

	// read all
	@Override
	public List<Pokemon> getAll() {
		return pokemonRepo.findAll();
	}

	// read
	@Override
	public Pokemon getById(Long id) {
		if (pokemonRepo.existsById(id)) {
			return pokemonRepo.findById(id).get();
		} else {
			return null;
			// TODO - Throw exception
		}
	}

	@Override
	public Pokemon create(Pokemon pokemon) {
		return pokemonRepo.save(pokemon);
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
			return null;
		}
		
		// TODO throw Exception
		
	}

	@Override
	public void delete(Long id) {
		
		if (pokemonRepo.existsById(id)) {
			pokemonRepo.deleteById(id);
		} else {
			System.out.println("Pokemon with " + id + " does not exist");;
		}
		// TODO throw exception

	}

}
