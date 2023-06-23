package com.skilldistillery.pawrentsplace.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Business {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String about;
	
	private String phone;
	
	@Column(name="image_url")
	private String imageUrl;
	
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@JsonIgnore
	@OneToMany(mappedBy="business")
	private List<BusinessRating> businessRatings;

<<<<<<< Updated upstream
=======

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "owner_uses_business", joinColumns = @JoinColumn(name = "business_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> clients;
	
	@ManyToMany
	@JoinTable(name = "service_provided_by_business", joinColumns = @JoinColumn(name = "business_id"), inverseJoinColumns = @JoinColumn(name = "service_type_id"))
	private List<ServiceType> serviceTypes;


>>>>>>> Stashed changes
	public Business() {
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


	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
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
	

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	

	public List<BusinessRating> getBusinessRatings() {
		return businessRatings;
	}

	public void setBusinessRatings(List<BusinessRating> businessRatings) {
		this.businessRatings = businessRatings;
<<<<<<< Updated upstream
=======
	}

	public List<User> getClients() {
		return clients;
	}

	public void setClients(List<User> clients) {
		this.clients = clients;
	}

	
	
	public List<ServiceType> getServiceTypes() {
		return serviceTypes;
	}

	public void setServiceTypes(List<ServiceType> serviceTypes) {
		this.serviceTypes = serviceTypes;
>>>>>>> Stashed changes
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
		Business other = (Business) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Business [id=" + id + ", name=" + name + ", about=" + about + ", phone=" + phone + ", imageUrl="
				+ imageUrl + "]";
	}

	
	
	

}
