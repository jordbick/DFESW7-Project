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

import com.qa.user_app.data.entity.Type;
import com.qa.user_app.data.repository.TypeRepository;
import com.qa.user_app.exceptions.ItemNotFoundException;

@ExtendWith(MockitoExtension.class)
public class TypeServiceUnitTest implements IServiceUnitTest{

	@Mock
	private TypeRepository typeRepo;
	
	@InjectMocks
	private TypeService typeService;
	
	private List<Type> type;
	private Type foundType;
	private Type typeWithoutId;
	private Type typeWithId;
	private Type updateDetails;
	private Type updatedType;
	private long nextTypeId;
	
	@BeforeEach
	public void init() {
		type = new ArrayList<>();
		type.addAll(List.of(new Type(1L, "Grass"), new Type(2L, "Fire"),
				new Type(3L, "Water")));

		foundType = new Type(1L, "Grass");
		int listSize = type.size();
		nextTypeId = type.get(listSize - 1).getId() + 1;
		typeWithoutId = new Type("Flying");
		typeWithId = new Type(nextTypeId, "Flying");
		updateDetails = new Type("Flying");
		updatedType = new Type(1L, "Flying");
	}
	
	@Override
	@Test
	public void getAllTest() {
		when(typeRepo.findAll()).thenReturn(type);

		assertThat(typeService.getAll()).isEqualTo(type);

		verify(typeRepo).findAll();
	}

	@Override
	@Test
	public void getByIdTest() {
		long id = foundType.getId();
		when(typeRepo.findById(id)).thenReturn(Optional.of(foundType));

		assertThat(typeService.getById(id)).isEqualTo(foundType);

		verify(typeRepo).findById(id);
	}

	@Override
	@Test
	public void getByInvalidIdTest() {
		when(typeRepo.findById(nextTypeId)).thenReturn(Optional.empty());

		ItemNotFoundException e = Assertions.assertThrows(ItemNotFoundException.class, () -> {
			typeService.getById(nextTypeId);
		});

		assertThat(e.getMessage()).isEqualTo("Type with id " + nextTypeId + " does not exist");

		verify(typeRepo).findById(nextTypeId);
	}

	@Override
	@Test
	public void createTest() {
		when(typeRepo.save(typeWithoutId)).thenReturn(typeWithId);

		assertThat(typeService.create(typeWithoutId)).isEqualTo(typeWithId);

		verify(typeRepo).save(typeWithoutId);
	}

	@Override
	@Test
	public void updateTest() {
		long id = foundType.getId();
		when(typeRepo.existsById(id)).thenReturn(true);
		when(typeRepo.getById(id)).thenReturn(foundType);
		when(typeRepo.save(foundType)).thenReturn(updatedType);

		assertThat(typeService.update(id, updateDetails)).isEqualTo(updatedType);

		verify(typeRepo).getById(id);
		verify(typeRepo).getById(id);
		verify(typeRepo).save(foundType);
	}

	@Override
	@Test
	public void updateInvalidTest() {
		when(typeRepo.existsById(nextTypeId)).thenReturn(false);

		ItemNotFoundException e = Assertions.assertThrows(ItemNotFoundException.class, () -> {
			typeService.update(nextTypeId, updateDetails);
		});

		assertThat(e.getMessage()).isEqualTo("Type with id " + nextTypeId + " does not exist");

		verify(typeRepo).existsById(nextTypeId);
	}

	@Override
	@Test
	public void deleteTest() {
		long id = foundType.getId();
		when(typeRepo.existsById(id)).thenReturn(true);

		typeService.delete(id);
		assertThat(Optional.empty()).isEqualTo(typeRepo.findById(id));

		verify(typeRepo).existsById(id);
		verify(typeRepo).deleteById(id);
	}

	@Override
	@Test
	public void deleteInvalidTest() {
		when(typeRepo.existsById(nextTypeId)).thenReturn(false);

		ItemNotFoundException e = Assertions.assertThrows(ItemNotFoundException.class, () -> {
			typeService.delete(nextTypeId);
		});

		assertThat(e.getMessage()).isEqualTo("Type with id " + nextTypeId + " does not exist");

		verify(typeRepo).existsById(nextTypeId);
	}

	
	
}
