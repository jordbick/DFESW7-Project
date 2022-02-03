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
import com.qa.user_app.data.entity.Type;
import com.qa.user_app.data.entity.Type;
import com.qa.user_app.data.repository.TypeRepository;
import com.qa.user_app.service.TypeService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class TypeControllerSystemIntegrationTest implements IControllerSystemIntegrationTest{
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private TypeRepository typeRepo;
	
	private List<Type> typeInDb;
	private long nextTypeId;
	
	@BeforeEach
	public void init() {
		List<Type> types = List.of(new Type("Grass"), new Type("Fire"), new Type("Water"));
		typeInDb = new ArrayList<>();
		typeInDb.addAll(typeRepo.saveAll(types));
		int dbSize = typeInDb.size();
		nextTypeId = typeInDb.get(dbSize - 1).getId() + 1;
	}
	
	@Test
	@Override
	public void getAllTest() throws Exception {
MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/type");
		
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		String typeReturned = objectMapper.writeValueAsString(typeInDb);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(typeReturned);
		
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);	
	}
	
	@Test
	@Override
	public void getByIdTest() throws Exception {
		Type findType = typeInDb.get(0);
		long id = findType.getId();
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, "/type/" + id);
		
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		String typeReturned = objectMapper.writeValueAsString(findType);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(typeReturned);
		
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);	
	}
	
	@Test
	@Override
	public void getByInvalidIdTest() throws Exception {
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.GET, "/type/" + nextTypeId);
		
		String returned = "Type with id " + nextTypeId + " does not exist";
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isNotFound();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string(returned);
		
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	@Test
	@Override
	public void createTest() throws Exception {
		Type newType = new Type("Flying");
		Type savedType = new Type(nextTypeId, newType.getName());
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/type");
		
		// Content type sets the type of data in the body of the request
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		// Content adds the fields to the body of the request
		// objectMapper sets the body of the request to a JSON string
		mockRequest.content(objectMapper.writeValueAsString(newType));
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(savedType));
		
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	@Test
	@Override
	public void updateTest() throws Exception {
		Type oldType = typeInDb.get(0);
		Type newType = new Type("Fighting");
		Type updatedType = new Type(oldType.getId(), newType.getName());
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "/type/" + oldType.getId());
		
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(newType)); 
		mockRequest.accept(MediaType.APPLICATION_JSON);

		String typeReturned = objectMapper.writeValueAsString(updatedType);
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(typeReturned);
		
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	@Test
	@Override
	public void updateInvalidTest() throws Exception {
		Type newType = new Type("Flying");
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "/type/" + nextTypeId);
		
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(objectMapper.writeValueAsString(newType)); 
		mockRequest.accept(MediaType.ALL);
		 
		
		String returned = "Type with id " + nextTypeId + " does not exist";
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isNotFound();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string(returned);
		
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
		
	}
	
	@Test
	@Override
	public void deleteTest() throws Exception {
		Type typeToDelete = typeInDb.get(0);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.DELETE, "/type/" + typeToDelete.getId());
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isAccepted();
		mockMvc.perform(mockRequest).andExpect(statusMatcher);
		assertEquals(Optional.empty(), typeRepo.findById(typeToDelete.getId()));
		
	}

	@Test
	@Override
	public void deleteInvalidTest() throws Exception {
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.DELETE, "/type/" + nextTypeId);
		
		String returned = "Type with id " + nextTypeId + " does not exist";
		
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isNotFound();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().string(returned);
		
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
		
	}

	
}
