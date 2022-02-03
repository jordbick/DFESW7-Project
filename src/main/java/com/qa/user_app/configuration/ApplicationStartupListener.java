package com.qa.user_app.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.qa.user_app.data.entity.Pokemon;
import com.qa.user_app.data.repository.PokemonRepository;


// Database filled with below details on startup in dev mode

@Profile("dev")
@Configuration
public class ApplicationStartupListener {
	
	private PokemonRepository pokemonRepo;
	
	@Autowired
	public ApplicationStartupListener(PokemonRepository pokemonRepo) {
		this.pokemonRepo = pokemonRepo;
	}
	

	public void onApplicationEvent(ApplicationReadyEvent event) {
		pokemonRepo.saveAll(List.of(
				new Pokemon(3, "Venasaur", false),
				new Pokemon(4, "Charmander", true),
				new Pokemon(5, "Charmeleon", true)
		));
	}

}
