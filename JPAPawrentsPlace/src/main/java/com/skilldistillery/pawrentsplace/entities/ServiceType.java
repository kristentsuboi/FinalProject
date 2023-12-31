package com.skilldistillery.pawrentsplace.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="service_type") 
public class ServiceType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	 
	private String description;
		
	@Column(name="image_url")
	private String imageUrl;
	
	@JsonIgnore
	@ManyToMany(mappedBy="serviceTypes")
	private List<Business> businesses;
	
	public ServiceType() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public List<Business> getBusinesses() {
		return businesses;
	}

	public void setBusinesses(List<Business> businesses) {
		this.businesses = businesses;
	}
	public void addBusiness(Business business) {
		if (businesses == null) {
			businesses = new ArrayList<>();
		}
		if (!businesses.contains(business)) {
			businesses.add(business);
			business.addServiceType(this);
		}
	}
	
	public void removeBusiness(Business business) {
		if (businesses != null && businesses.contains(business)) {
			businesses.remove(business);
			business.removeServiceType(this);
		}
	}
	

	@Override
	public String toString() {
		return "ServiceType [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl
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
		ServiceType other = (ServiceType) obj;
		return id == other.id;
	}
	
	

}
