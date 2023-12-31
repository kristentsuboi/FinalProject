package com.skilldistillery.pawrentsplace.entities;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


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
	@OneToMany(mappedBy="business")
	private List<User> employees;
	
	@JsonIgnoreProperties({"business", "user"})
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	

	@JsonIgnore
	@OneToMany(mappedBy="business")
	private List<BusinessRating> businessRatings;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "owner_uses_business", joinColumns = @JoinColumn(name = "business_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> clients;
	
	@JsonIgnoreProperties({"businesses"})
	@ManyToMany
	@JoinTable(name = "service_provided_by_business", 
	joinColumns = @JoinColumn(name = "business_id"), 
	inverseJoinColumns = @JoinColumn(name = "service_type_id"))
	private List<ServiceType> serviceTypes;


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
	
	public List<User> getEmployees() {
		return employees;
	}

	public void setEmployees(List<User> employees) {
		this.employees = employees;
	}	


	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;

	}
	
	
	
	public void addEmployee(User user) {
		if (employees == null) {
			employees = new ArrayList<>();
		}
		if (!employees.contains(user)) {
			employees.add(user);
			user.setBusiness(this);;
		}
	}
	
	public void removeEmployee(User user) {
		if (employees != null && employees.contains(user)) {
			employees.remove(user);
			user.setBusiness(null);
		}
	}
	public void addServiceType(ServiceType service) {
		if (serviceTypes == null) {
			serviceTypes = new ArrayList<>();
		}
		if (!serviceTypes.contains(service)) {
			serviceTypes.add(service);
			service.addBusiness(this);
		}
	}
	
	public void removeServiceType(ServiceType service) {
		if (serviceTypes != null && serviceTypes.contains(service)) {
			serviceTypes.remove(service);
			service.removeBusiness(this);
		}
	}
	
	

	public List<BusinessRating> getBusinessRatings() {
		return businessRatings;
	}

	public void setBusinessRatings(List<BusinessRating> businessRatings) {
		this.businessRatings = businessRatings;
	}

	public List<User> getClients() {
		return clients;
	}

	public void setClients(List<User> clients) {
		this.clients = clients;
	}

	public void addClient(User client) {
		if (clients == null) {
			clients = new ArrayList<>();
		}
		if (!clients.contains(client)) {
			clients.add(client);
			client.addBusinessUsed(this);
		}
	}
	
	public void removeClient(User client) {
		if (clients != null && clients.contains(client)) {
			clients.remove(client);
			client.removeBusinessUsed(this);
		}
	}
	
	public List<ServiceType> getServiceTypes() {
		return serviceTypes;
	}

	public void setServiceTypes(List<ServiceType> serviceTypes) {
		this.serviceTypes = serviceTypes;
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
