package com.skilldistillery.pawrentsplace.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Medication {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(name = "last_administered")
	private LocalDate lastAdministered;
	
	private String frequency;
	
	@Column(name="with_food")
	private Boolean withFood;
	
	@Column(name="date_started")
	private LocalDate dateStarted;
	
	private String notes;
	
	@ManyToOne
	@JoinColumn(name="pet_id")
	private Pet pet; 
	
	
	
	public Medication() {
		super();
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LocalDate getLastAdministered() {
		return lastAdministered;
	}


	public void setLastAdministered(LocalDate lastAdministered) {
		this.lastAdministered = lastAdministered;
	}


	public String getFrequency() {
		return frequency;
	}


	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Boolean getWithFood() {
		return withFood;
	}

	public void setWithFood(Boolean withFood) {
		this.withFood = withFood;
	}

	public LocalDate getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(LocalDate dateStarted) {
		this.dateStarted = dateStarted;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	
	
	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	@Override
	public String toString() {
		return "Medication [id=" + id + ", name=" + name + ", lastAdministered=" + lastAdministered + ", frequency="
				+ frequency + ", withFood=" + withFood + ", dateStarted=" + dateStarted + ", notes=" + notes + "]";
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medication other = (Medication) obj;
		return id == other.id;
	}


	

}
