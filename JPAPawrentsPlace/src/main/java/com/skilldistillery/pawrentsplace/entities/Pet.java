package com.skilldistillery.pawrentsplace.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	private LocalDate birth;
	
	private String color;
	
	private String about;
	
	private boolean microchipped;
	
	@Column(name="image_url")
	private String imageUrl;
	
	private String allergies;
	
	private boolean enabled;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@JsonIgnoreProperties({"pets", "business", "comments", "businessesUsed", "medicalNotes", "petComments", "petClients"})
	@ManyToOne
	@JoinColumn(name="owner_id")
	private User user;
	
	@JsonIgnoreProperties({"pet"})
	@OneToMany(mappedBy="pet")
	private List<Diet> diets;

	@JsonIgnoreProperties({"pet"})
	@OneToMany(mappedBy="pet")
	private List<Shot> shots;
	
	@JsonIgnoreProperties({"pet"})
	@OneToMany(mappedBy="pet")
	private List<Medication> medications;
	
	@JsonIgnoreProperties({"pet"})
	@OneToMany(mappedBy="pet")
	private List<PetComment> petComments;
	
	@JsonIgnoreProperties(value={"pet"}, allowSetters=true)
	@OneToMany(mappedBy="pet")
	private List<MedicalNote> medicalNotes;
	
	@JsonIgnoreProperties({"pets", "petClients", "comments", "businessesUsed", "medicalNotes", "petComments"})
	@ManyToMany(mappedBy="petClients")
	private List<User> providers;
	
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

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
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

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	

	public List<Diet> getDiets() {
		return diets;
	}

	public void setDiets(List<Diet> diets) {
		this.diets = diets;
	}
	
	

	public List<Shot> getShots() {
		return shots;
	}

	public void setShots(List<Shot> shots) {
		this.shots = shots;
	}

	public List<PetComment> getPetComments() {
		return petComments;
	}

	public void setPetComments(List<PetComment> petComments) {
		this.petComments = petComments;
	}

	public List<Medication> getMedications() {
		return medications;
	}

	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}
	

	public List<MedicalNote> getMedicalNotes() {
		return medicalNotes;
	}

	public void setMedicalNotes(List<MedicalNote> medicalNotes) {
		this.medicalNotes = medicalNotes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<User> getProviders() {
		return providers;
	}

	public void setProviders(List<User> providers) {
		this.providers = providers;
	}
	
	public void addProvider(User provider) {
		if (providers == null) {
			providers = new ArrayList<>();
		}
		if (!providers.contains(provider)) {
			providers.add(provider);
			provider.addPetClient(this);
		}
	}
	
	public void removeProvider(User provider) {
		if (providers != null && providers.contains(provider)) {
			providers.remove(provider);
			provider.removePetClient(this);
		}
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
	
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pet [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", species=");
		builder.append(species);
		builder.append(", breed=");
		builder.append(breed);
		builder.append(", height=");
		builder.append(height);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", birth=");
		builder.append(birth);
		builder.append(", color=");
		builder.append(color);
		builder.append(", about=");
		builder.append(about);
		builder.append(", microchipped=");
		builder.append(microchipped);
		builder.append(", imageUrl=");
		builder.append(imageUrl);
		builder.append(", allergies=");
		builder.append(allergies);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
