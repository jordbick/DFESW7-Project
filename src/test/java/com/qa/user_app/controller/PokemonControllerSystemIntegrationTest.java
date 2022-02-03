package com.qa.user_app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.user_app.data.entity.Pokemon;
import com.qa.user_app.data.repository.PokemonRepository;
import com.qa.user_app.exceptions.ItemNotFoundException;


// random port selected for testing web environment
// useful for parallel testing
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
// Reset state of db after every test
@Transactional
public class PokemonControllerSystemIntegrationTest implements IControllerSystemIntegrationTest{
	
	// For sending test HTTP requests
	@Autowired
	private MockMvc mockMvc;
	
	// For serialisation and deserialisation of data to/from JSON
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private PokemonRepository pokeRepo;
	
	private List<Pokemon> pokemonInDb;
	private long nextPokemonId;
	
	// Initialise  database before each test
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
	public void getAllTest() throws Exception {
		// mock web request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/pokemon");
		
		// Specify accepted media (JSON)
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		// Expected content returned from mock request
		String pokemonReturned = objectMapper.writeValueAsString(pokemonInDb);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(pokemonReturned);
		
		// Send the mockRequest and assert the results are as expected (above)
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	// READ by id test
	@Test
	public void getByIdTest() throws Exception{
		Pokemon findPokemon = pokemonInDb.get(0);
		long id = findPokemon.getId();
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, "/pokemon/" + id);
		
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		String pokemonReturned = objectMapper.writeValueAsString(findPokemon);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(pokemonReturned);
		
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	// READ by invalid id test
	@Test
	public void getByInvalidIdTest() throws Exception{
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, "/pokemon/" + nextPokemonId);
		
		String returned = "Pokemon with id " + nextPokemonId + " does not exist";
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isNotFound();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string(returned);
		
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	// CREATE test
	@Test
	public void createTest() throws Exception{
		Pokemon newPokemon = new Pokemon(4, "Charmander", true);
		Pokemon savedPokemon = new Pokemon
						(nextPokemonId, 
						newPokemon.getPokedexNumber(), 
						newPokemon.getName(), 
						newPokemon.getCanEvolve());
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/pokemon");
		
		// Content type sets the type of data in the body of the request
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		// Content adds the fields to the body of the request
		// objectMapper sets the body of the request to a JSON string
		mockRequest.content(objectMapper.writeValueAsString(newPokemon));
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(savedPokemon));
		
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	// CREATE with pokemon that already exists test
	@Test
	public void createInvalidTest() throws Exception{
		Pokemon newPokemon = new Pokemon(4, "Bulbasaur", true);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/pokemon");
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(newPokemon));
		
		String returned = "Pokemon with name " + newPokemon.getName() + " already exists";
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isConflict();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string(returned);
		
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
		
	}
	
	
	// UPDATE test
	@Test
	public void updateTest() throws Exception {
		Pokemon oldPokemon = pokemonInDb.get(0);
		Pokemon newPokemon = new Pokemon
						(oldPokemon.getPokedexNumber(),
						"Pikachu",
						oldPokemon.getCanEvolve());
		Pokemon updatedPokemon = new Pokemon
						(oldPokemon.getId(), 
						newPokemon.getPokedexNumber(), 
						newPokemon.getName(), 
						newPokemon.getCanEvolve());
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "/pokemon/" + oldPokemon.getId());
		
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(newPokemon)); 
		mockRequest.accept(MediaType.APPLICATION_JSON);

		String pokemonReturned = objectMapper.writeValueAsString(updatedPokemon);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(pokemonReturned);
		
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	// UPDATE by invalid ID test
	@Test
	public void updateInvalidTest() throws Exception {
		Pokemon newPokemon = new Pokemon(4, "Charizard", true);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "/pokemon/" + nextPokemonId);
		
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(newPokemon)); 
		mockRequest.accept(MediaType.ALL);
		 
		
		String returned = "Pokemon with id " + nextPokemonId + " does not exist";
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isNotFound();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string(returned);
		
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	// DELETE test
	@Test
	public void deleteTest() throws Exception {
		Pokemon pokemonToDelete = pokemonInDb.get(0);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.DELETE, "/pokemon/" + pokemonToDelete.getId());
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();
		mockMvc.perform(mockRequest).andExpect(statusMatcher);
		assertEquals(Optional.empty(), pokeRepo.findById(pokemonToDelete.getId()));
	}

	
	
	// DELETE by invalid ID test
	@Test
	public void deleteInvalidTest() throws Exception {		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.DELETE, "/pokemon/" + nextPokemonId);
		
		String returned = "Pokemon with id " + nextPokemonId + " does not exist";
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isNotFound();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string(returned);
		
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
}
