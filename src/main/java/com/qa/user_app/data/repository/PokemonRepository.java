package com.qa.user_app.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.user_app.data.entity.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer>{

}
