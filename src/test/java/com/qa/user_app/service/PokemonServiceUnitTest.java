package com.qa.user_app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.qa.user_app.data.entity.Pokemon;
import com.qa.user_app.data.repository.PokemonRepository;
import com.qa.user_app.exceptions.ItemNotFoundException;
import com.qa.user_app.exceptions.PokemonAlreadyExists;

// Tests for each method in PokemonService

//JUnit test runner
@ExtendWith(MockitoExtension.class)
public class PokemonServiceUnitTest {

	@Mock // MockBean
	private PokemonRepository pokemonRepo;

	@InjectMocks // equivalent to Autowired
	private PokemonService pokemonService;

	// Creating fields to be tested
	private List<Pokemon> pokemon;
	private Pokemon foundPokemon;
	private Pokemon invalidPokemon;
	private Pokemon pokemonWithoutId;
	private Pokemon pokemonWithId;
	private Pokemon updateDetails;
	private Pokemon updatedPokemon;

	// Initialises data to the above before each test runs
	@BeforeEach
	public void init() {
		pokemon = new ArrayList<>();
		pokemon.addAll(List.of(new Pokemon(1L, 1, "Bulbasaur", true), new Pokemon(2L, 2, "Ivysaur", true),
				new Pokemon(3L, 3, "Venusaur", false)));

		foundPokemon = new Pokemon(1L, 1, "Bulbasaur", true);
		invalidPokemon = new Pokemon(1, "Bulbasaur", true);
		pokemonWithoutId = new Pokemon(4, "Charmander", true);
		pokemonWithId = new Pokemon(4L, 4, "Charmander", true);
		updateDetails = new Pokemon(2, "Bulbasaur", true);
		updatedPokemon = new Pokemon(1L, 2, "Bulbasaur", true);
	}

	// READ all test
	@Test
	public void getAllPokemonTest() {
		when(pokemonRepo.findAll()).thenReturn(pokemon);

		assertThat(pokemonService.getAll()).isEqualTo(pokemon);

		verify(pokemonRepo).findAll();
	}

	// READ by id test
	@Test
	public void getPokemonByIdTest() {
		long id = foundPokemon.getId();
		when(pokemonRepo.findById(id)).thenReturn(Optional.of(foundPokemon));

		assertThat(pokemonService.getById(id)).isEqualTo(foundPokemon);

		verify(pokemonRepo).findById(id);
	}

	// READ by invalid id test
	@Test
	public void getPokemonByInvalidIdTest() {
		long id = 60L;
		when(pokemonRepo.findById(id)).thenReturn(Optional.empty());

		ItemNotFoundException e = Assertions.assertThrows(ItemNotFoundException.class, () -> {
			pokemonService.getById(id);
		});

		assertThat(e.getMessage()).isEqualTo("Pokemon with id " + id + " does not exist");

		verify(pokemonRepo).findById(id);
	}

	// CREATE test
	@Test
	public void createPokemonTest() {
		when(pokemonRepo.existsByName(pokemonWithoutId.getName())).thenReturn(false);
		when(pokemonRepo.save(pokemonWithoutId)).thenReturn(pokemonWithId);

		assertThat(pokemonService.create(pokemonWithoutId)).isEqualTo(pokemonWithId);

		verify(pokemonRepo).existsByName(pokemonWithoutId.getName());
		verify(pokemonRepo).save(pokemonWithoutId);
	}

	// CREATE with pokemon that already exists test
	@Test
	public void createExistingPokemonTest() {
		when(pokemonRepo.existsByName(invalidPokemon.getName())).thenReturn(true);

		PokemonAlreadyExists e = Assertions.assertThrows(PokemonAlreadyExists.class, () -> {
			pokemonService.create(invalidPokemon);
		});

		assertThat(e.getMessage()).isEqualTo("Pokemon with name " + invalidPokemon.getName() + " already exists");

		verify(pokemonRepo).existsByName(invalidPokemon.getName());
	}

	// UPDATE test
	@Test
	public void updatePokemonTest() {
		long id = foundPokemon.getId();
		when(pokemonRepo.existsById(id)).thenReturn(true);
		when(pokemonRepo.getById(id)).thenReturn(foundPokemon);
		when(pokemonRepo.save(foundPokemon)).thenReturn(updatedPokemon);

		assertThat(pokemonService.update(id, updateDetails)).isEqualTo(updatedPokemon);

		verify(pokemonRepo).getById(id);
		verify(pokemonRepo).getById(id);
		verify(pokemonRepo).save(foundPokemon);

	}

	// UPDATE test with invalid id
	@Test
	public void updatePokemonInvalidTest() {
		long id = 43L;
		when(pokemonRepo.existsById(id)).thenReturn(false);

		ItemNotFoundException e = Assertions.assertThrows(ItemNotFoundException.class, () -> {
			pokemonService.update(id, updateDetails);
		});

		assertThat(e.getMessage()).isEqualTo("Pokemon with id " + id + " does not exist");

		verify(pokemonRepo).existsById(id);
	}

	// DELETE test
	@Test
	public void deletePokemonTest() {
		long id = foundPokemon.getId();
		when(pokemonRepo.existsById(id)).thenReturn(true);

		pokemonService.delete(id);
		assertThat(Optional.empty()).isEqualTo(pokemonRepo.findById(id));

		verify(pokemonRepo).existsById(id);
		verify(pokemonRepo).deleteById(id);
	}

	// DELETE test with invalid id
	@Test
	public void deleteInvalidPokemonTest() {
		long id = 56L;
		when(pokemonRepo.existsById(id)).thenReturn(false);

		ItemNotFoundException e = Assertions.assertThrows(ItemNotFoundException.class, () -> {
			pokemonService.delete(id);
		});

		assertThat(e.getMessage()).isEqualTo("Pokemon with id " + id + " does not exist");

		verify(pokemonRepo).existsById(id);
	}

}
