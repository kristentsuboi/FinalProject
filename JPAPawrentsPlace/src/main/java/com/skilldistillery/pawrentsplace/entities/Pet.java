package com.skilldistillery.pawrentsplace.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String species;
	
	private String breed;
	
	private int height;
	
	private int weight;
	
	private String gender;
	
	private int age;
	
	@Column(name="age_interval")
	private String ageInterval;
	
	private String color;
	
	private String about;
	
	private boolean microchipped;
	
	@Column(name="image_url")
	private String imageUrl;

	public Pet() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAgeInterval() {
		return ageInterval;
	}

	public void setAgeInterval(String ageInterval) {
		this.ageInterval = ageInterval;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public boolean isMicrochipped() {
		return microchipped;
	}

	public void setMicrochipped(boolean microchipped) {
		this.microchipped = microchipped;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", species=" + species + ", breed=" + breed + ", height=" + height
				+ ", weight=" + weight + ", gender=" + gender + ", age=" + age + ", ageInterval=" + ageInterval
				+ ", color=" + color + ", about=" + about + ", microchipped=" + microchipped + ", imageUrl=" + imageUrl
				+ "]";
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
		Pet other = (Pet) obj;
		return id == other.id;
	}
	
	

}
