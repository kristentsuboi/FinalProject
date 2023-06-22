package com.skilldistillery.pawrentsplace.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "medical_history")
public class MedicalHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String allergies;
	private String notes;
	
	public MedicalHistory() {
		super();
	}

	public MedicalHistory(int id, String allergies, String notes) {
		super();
		this.id = id;
		this.allergies = allergies;
		this.notes = notes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
		MedicalHistory other = (MedicalHistory) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "MedicalHistory [id=" + id + ", allergies=" + allergies + ", notes=" + notes + "]";
	}
	
	
	
	
	
	
	
}
