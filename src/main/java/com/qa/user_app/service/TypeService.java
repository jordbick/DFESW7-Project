package com.qa.user_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.user_app.data.entity.Type;
import com.qa.user_app.data.repository.TypeRepository;

@Service
public class TypeService implements IService<Type>{

	private TypeRepository typeRepo;
	
	@Autowired
	public TypeService(TypeRepository typeRepo) {
		this.typeRepo = typeRepo;
	}
	
	@Override
	public List<Type> getAll() {
		return typeRepo.findAll();
	}

	@Override
	public Type getById(Long id) {
		if (typeRepo.existsById(id)) {
			return typeRepo.findById(id).get();
		} else {
			return null;
		}
	}

	@Override
	public Type create(Type type) {
		return typeRepo.save(type);
	}

	@Override
	public Type update(Long id, Type type) {
		if (typeRepo.existsById(id)) {
			Type updatedType = typeRepo.getById(id);
			updatedType.setName(type.getName());
			updatedType.setPokemon(type.getPokemon());
			return typeRepo.save(updatedType);
		} else {
			return null;
		}
	}

	@Override
	public void delete(Long id) {
		if (typeRepo.existsById(id)) {
			typeRepo.deleteById(id);
		} else {
			System.out.println("Type with " + id + " does not exist");;
		}
		
	}

}
