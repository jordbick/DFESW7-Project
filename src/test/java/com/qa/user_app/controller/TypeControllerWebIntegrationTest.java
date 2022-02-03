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

import com.qa.user_app.data.entity.Type;
import com.qa.user_app.service.TypeService;

@WebMvcTest(TypeController.class)
public class TypeControllerWebIntegrationTest implements IControllerWebIntegrationTest{
	
	@Autowired
	private TypeController typeController;
	
	@MockBean
	private TypeService typeService;
	
	private List<Type> type;
	private Type typeWithoutId;
	private Type typeWithId;
	private Type foundType;
	private Type updatedType;
	
	@BeforeEach
	public void init() {
		type = new ArrayList<>();
		type.addAll(List.of(new Type(1L, "Grass"), new Type(2L, "Fire"), new Type(3L, "Water")));
		foundType = new Type(1L, "Grass");
		int listSize = type.size();
		long nextTypeId = type.get(listSize - 1).getId() + 1;
		typeWithoutId = new Type("Flying");
		updatedType = new Type(1L, "Flying");
		typeWithId = new Type(nextTypeId, "Flying");
		
	}

	@Override
	@Test
	public void getAllTest() {
		ResponseEntity<List<Type>> expected = new ResponseEntity<List<Type>>(type, HttpStatus.OK);
		
		when(typeService.getAll()).thenReturn(type);
		
		ResponseEntity<List<Type>> actual = typeController.getAll();
		assertThat(expected).isEqualTo(actual);

		verify(typeService).getAll();
		
	}

	@Override
	@Test
	public void getById() {
		ResponseEntity<Type> expected = ResponseEntity.of(Optional.of(foundType));
		
		when(typeService.getById(foundType.getId())).thenReturn(foundType);
		
		ResponseEntity<Type> actual = typeController.getById(foundType.getId());
		
		assertThat(expected).isEqualTo(actual);
		
		verify(typeService).getById(foundType.getId());
		
	}

	@Override
	@Test
	public void createTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/type/" + String.valueOf(typeWithId.getId()));
		
		ResponseEntity<Type> expected = new ResponseEntity<Type>
		// body 	httpHeaders		responseStatusCode
		(typeWithId, headers, HttpStatus.CREATED);
		
		when(typeService.create(typeWithoutId)).thenReturn(typeWithId);
		
		ResponseEntity<Type> actual = typeController.create(typeWithoutId);
		assertThat(expected).isEqualTo(actual);
		
		verify(typeService).create(typeWithoutId);
	}

	@Override
	@Test
	public void updateTest() {
		ResponseEntity<Type> expected = new ResponseEntity<Type>(updatedType, HttpStatus.ACCEPTED);
		
		when(typeService.update(foundType.getId(), typeWithoutId)).thenReturn(updatedType);
		
		ResponseEntity<Type> actual = typeController.update(foundType.getId(), typeWithoutId);
		
		assertThat(expected).isEqualTo(actual);
		verify(typeService).update(foundType.getId(), typeWithoutId);
	}

	@Override
	@Test
	public void deleteTest() {
		ResponseEntity<?> expected = ResponseEntity.accepted().build();
		ResponseEntity<?> actual = typeController.delete(foundType.getId());
		
		assertThat(expected).isEqualTo(actual);
		
		verify(typeService).delete(foundType.getId());
		
	}

}
