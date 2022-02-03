package com.qa.user_app.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.user_app.data.entity.Pokemon;

// Declare interface which extends Spring Data JPA's JpaRepository (provides those functionalities)
// Domain type as Pokemon and if type as Long

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long>{
	
	// Checks if Pokemon with inputted name exists
	// Returns true if name exists, otherwise returns false
	public boolean existsByName(String name);

}
