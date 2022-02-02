package com.qa.user_app.data.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "pokemon")
public class Pokemon {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// automatically generated id
	private Long id;

	@NotNull
	@Min(1)
	@Max(898)
	// range of pokedex entries
	@Column(name = "pokedex_number")
	private Integer pokedexNumber;

	@NotNull
	private String name;

	@NotNull
	@Column(name = "can_evolve", columnDefinition = "TINYINT")
	private Boolean canEvolve;

	@OneToMany(mappedBy = "pokemon", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Type> type;

	
	// no arg constructor
	public Pokemon() {
		super();
	}

	public Pokemon(Integer pokedexNumber, String name, Boolean canEvolve) {
		super();
		this.pokedexNumber = pokedexNumber;
		this.name = name;
		this.canEvolve = canEvolve;
	}

	public Pokemon(Long id, Integer pokedexNumber, String name, Boolean canEvolve) {
		super();
		this.id = id;
		this.pokedexNumber = pokedexNumber;
		this.name = name;
		this.canEvolve = canEvolve;
	}

	// Getters and Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<Type> getType() {
		return type;
	}

	public void setType(List<Type> type) {
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
