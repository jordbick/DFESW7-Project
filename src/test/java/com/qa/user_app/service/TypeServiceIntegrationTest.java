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

import com.qa.user_app.data.entity.Type;
import com.qa.user_app.data.repository.TypeRepository;

@SpringBootTest
@Transactional
public class TypeServiceIntegrationTest implements IServiceIntegrationTest{

	@Autowired
	private TypeService typeService;
	
	@Autowired
	private TypeRepository typeRepo;
	
	
	private List<Type> typeInDb;
	private long nextTypeId;
	
	// Initialise database before each test
	@BeforeEach 
	public void init() {
		List<Type> type = List.of(new Type(1L, "Grass"), new Type(2L, "Fire"),
				new Type(3L, "Water"));
		typeInDb = new ArrayList<>();
		typeInDb.addAll(typeRepo.saveAll(type));
		int dbSize = typeInDb.size();
		// To determine what the id of the next Type added to the database would be
		// - we take the size and minus one (- 1) to get to the last element in the ArrayList
		// - then add one (+ 1) to the last element's id to determine the next id
		nextTypeId = typeInDb.get(dbSize - 1).getId() + 1;
	}
	
	
	
	@Override
	@Test
	public void getAllTest() {
		assertThat(typeInDb).isEqualTo(typeService.getAll());
	}

	@Override
	@Test
	public void getByIdTest() {
		Type findType = typeInDb.get(0);
		assertThat(typeService.getById(findType.getId())).isEqualTo(findType);
		
	}

	@Override
	@Test
	public void createTest() {
		Type newType = new Type("Flying");
		Type createdType = new Type(nextTypeId, newType.getName());
		
		assertThat(createdType).isEqualTo(typeService.create(newType));
		
	}

	@Override
	@Test
	public void updateTest() {
		Type oldType = typeInDb.get(0);
		Type newType = new Type("Flying");
		Type updatedType = typeService.update(oldType.getId(), newType);
		assertThat(oldType).isEqualTo(updatedType);
		
	}

	@Override
	@Test
	public void deleteTest() {
		Type typeToDelete = typeInDb.get(0);
		typeService.delete(typeToDelete.getId());
		assertThat(typeRepo.findById(typeToDelete.getId())).isEqualTo(Optional.empty());
		
	}
	
	

}
