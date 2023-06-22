package com.skilldistillery.pawrentsplace.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Diet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String type;
	private String frequency;
	private String notes;
	private String amount;
	
	
	public Diet() {
		super();
	}


	public Diet(int id, String name, String type, String frequency, String notes, String amount) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.frequency = frequency;
		this.notes = notes;
		this.amount = amount;
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


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
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


	public String getAmount() {
		return amount;
	}


	public void setAmount(String amount) {
		this.amount = amount;
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
		Diet other = (Diet) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return "Diet [id=" + id + ", name=" + name + ", type=" + type + ", frequency=" + frequency + ", notes=" + notes
				+ ", amount=" + amount + "]";
	}
	
	
	
	
	
	

}
