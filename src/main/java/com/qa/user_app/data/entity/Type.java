package com.qa.user_app.data.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "type")
public class Type {

	// IDENTIFIER
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "pokemon_type", nullable = false)
	private String name;

	@ManyToOne(targetEntity = Pokemon.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "pokemon_id")
	@JsonBackReference
	private Pokemon pokemon;

	public Type() {
		super();
	}
	
	public Type(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, pokemon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Type other = (Type) obj;
		return id == other.id && Objects.equals(name, other.name) && Objects.equals(pokemon, other.pokemon);
	}

	@Override
	public String toString() {
		return  name;
	}
	
	

}
