package com.skilldistillery.pawrentsplace.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medication {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(name = "last_administered")
	private LocalDateTime lastAdministered;
	private String frequency;
	
	
	public Medication() {
		super();
	}


	public Medication(int id, String name, LocalDateTime lastAdministered, String frequency) {
		super();
		this.id = id;
		this.name = name;
		this.lastAdministered = lastAdministered;
		this.frequency = frequency;
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


	public LocalDateTime getLastAdministered() {
		return lastAdministered;
	}


	public void setLastAdministered(LocalDateTime lastAdministered) {
		this.lastAdministered = lastAdministered;
	}


	public String getFrequency() {
		return frequency;
	}


	public void setFrequency(String frequency) {
		this.frequency = frequency;
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


	@Override
	public String toString() {
		return "Medication [id=" + id + ", name=" + name + ", lastAdministered=" + lastAdministered + ", frequency="
				+ frequency + "]";
	}
	
	
	
	
	
	

}
