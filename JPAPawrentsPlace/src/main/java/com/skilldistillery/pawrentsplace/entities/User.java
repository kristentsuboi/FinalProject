package com.skilldistillery.pawrentsplace.entities;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	private String password;
	
	private boolean enabled;
	
	private String role;
	
	private String email;
	
	private String phone;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	

	@ManyToOne
	@JoinColumn(name="business_id")
	private Business business; 
	
	@OneToOne
	@JoinColumn(name="address_id")
	private Address address; 
	
	@JsonIgnoreProperties({"user"})
	@OneToMany(mappedBy="user")
	private List<Comment> comments;
	
	@ManyToMany(mappedBy="clients")
	private List<Business> businessesUsed;
	
	@OneToMany(mappedBy="user")
	private List<MedicalNote> medicalNotes;
	
	@JsonIgnoreProperties({"user", "pet"})
	@OneToMany(mappedBy="user")
	private List<PetComment> petComments;
	
	@JsonIgnoreProperties({"user", "medicalNotes"})
	@OneToMany(mappedBy="user")
	private List<Pet> pets;
	

	@JsonIgnoreProperties(value={"user", "providers", "petComments", "medicalNotes"}, allowSetters=true)
	@ManyToMany
	@JoinTable(name = "pet_provider", joinColumns = @JoinColumn(name = "provider_id"), inverseJoinColumns = @JoinColumn(name = "pet_id"))
	private List<Pet> petClients;


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	
	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Business> getBusinessesUsed() {
		return businessesUsed;
	}

	public void setBusinessesUsed(List<Business> businessesUsed) {
		this.businessesUsed = businessesUsed;
	}
	
	public void addBusinessUsed(Business business) {
		if (businessesUsed == null) {
			businessesUsed = new ArrayList<>();
		}
		if (!businessesUsed.contains(business)) {
			businessesUsed.add(business);
			business.addClient(this);
		}
	}
	
	public void removeBusinessUsed(Business business) {
		if (businessesUsed != null && businessesUsed.contains(business)) {
			businessesUsed.remove(business);
			business.removeClient(this);
		}
	}
	
	public List<MedicalNote> getMedicalNotes() {
		return medicalNotes;
	}

	public void setMedicalNotes(List<MedicalNote> medicalNotes) {
		this.medicalNotes = medicalNotes;
	}

	public List<PetComment> getPetComments() {
		return petComments;
	}

	public void setPetComments(List<PetComment> petComments) {
		this.petComments = petComments;
	}

	
	
	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public List<Pet> getPetClients() {
		return petClients;
	}

	public void setPetClients(List<Pet> petClients) {
		this.petClients = petClients;
	}

	public void addPetClient(Pet pet) {
		if (petClients == null) {
			petClients = new ArrayList<>();
		}
		if (!petClients.contains(pet)) {
			petClients.add(pet);
			pet.addProvider(this);
		}
	}
	
	public void removePetClient(Pet pet) {
		if (petClients != null && petClients.contains(pet)) {
			petClients.remove(pet);
			pet.removeProvider(this);
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", role=");
		builder.append(role);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", imageUrl=");
		builder.append(imageUrl);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}
	
}