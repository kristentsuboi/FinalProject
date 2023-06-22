package com.skilldistillery.pawrentsplace.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Shot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(name="date_administered")
	private LocalDate dateAdministered;
	
	private String frequency;
	
	private String notes;

	@ManyToOne
	@JoinColumn(name="pet_id")
	private Pet pet;
	
	public Shot() {
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

	public LocalDate getDateAdministered() {
		return dateAdministered;
	}

	public void setDateAdministered(LocalDate dateAdministered) {
		this.dateAdministered = dateAdministered;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
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
		Shot other = (Shot) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shot [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", dateAdministered=");
		builder.append(dateAdministered);
		builder.append(", frequency=");
		builder.append(frequency);
		builder.append(", notes=");
		builder.append(notes);
		builder.append("]");
		return builder.toString();
	}
	
	


}
