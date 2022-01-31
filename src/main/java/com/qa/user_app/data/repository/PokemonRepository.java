package com.qa.user_app.data.repository;

<<<<<<< refs/remotes/origin/repo
public interface PokemonRepository {
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.user_app.data.entity.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer>{
>>>>>>> DFP-9 #comment Created pokemon repository

}
