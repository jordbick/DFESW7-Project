package com.qa.user_app.data.entity;

import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pokemon")
public class Pokemon {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Min(1)
	@Max(898)
	private Integer pokedexNumber;

	@NotNull
	private String name;

	@NotNull
	private Boolean canEvolve;

	@NotNull
	// second may be null if only has one type
	// Will have to set to string to be able to input into MySQL
	private ArrayList<PokemonType> type;

	public Pokemon() {
		super();
	}

	public Pokemon(Integer pokedexNumber, String name, Boolean canEvolve, ArrayList<PokemonType> type) {
		super();
		this.pokedexNumber = pokedexNumber;
		this.name = name;
		this.canEvolve = canEvolve;
		this.type = type;
	}

	public Pokemon(Integer id, Integer pokedexNumber, String name, Boolean canEvolve, ArrayList<PokemonType> type) {
		super();
		this.id = id;
		this.pokedexNumber = pokedexNumber;
		this.name = name;
		this.canEvolve = canEvolve;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPokedexNumber() {
		return pokedexNumber;
	}

	public void setPokedexNumber(Integer pokedexNumber) {
		this.pokedexNumber = pokedexNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getCanEvolve() {
		return canEvolve;
	}

	public void setCanEvolve(Boolean canEvolve) {
		this.canEvolve = canEvolve;
	}

	public ArrayList<PokemonType> getType() {
		return type;
	}

	public void setType(ArrayList<PokemonType> type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", pokedexNumber=" + pokedexNumber + ", name=" + name + ", canEvolve=" + canEvolve
				+ ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(canEvolve, id, name, pokedexNumber, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		return Objects.equals(canEvolve, other.canEvolve) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(pokedexNumber, other.pokedexNumber)
				&& Objects.equals(type, other.type);
	}

	
	


}
