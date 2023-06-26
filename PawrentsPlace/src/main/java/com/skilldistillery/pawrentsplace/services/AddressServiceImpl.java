package com.skilldistillery.pawrentsplace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.pawrentsplace.entities.Address;
import com.skilldistillery.pawrentsplace.entities.Business;
import com.skilldistillery.pawrentsplace.entities.User;
import com.skilldistillery.pawrentsplace.repositories.AddressRepository;
import com.skilldistillery.pawrentsplace.repositories.BusinessRepository;
import com.skilldistillery.pawrentsplace.repositories.UserRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BusinessRepository businessRepo;


	@Override
	public Address show(String username, int addressId) {
		Address address = addressRepo.findById(addressId).orElse(null);
		if (address != null && address.getUser().getUsername().equals(username)) {
			return address;
		}
		return null;
	}

	@Override
	public Address create(String username, Address address) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			address.setUser(user);
			user.setAddress(address);
			return addressRepo.saveAndFlush(address);
		}
		return null;
	}

	@Override
	public Address update(String username, int addressId, Address address) {
		Address existingAddress = addressRepo.findById(addressId).orElse(null);
		if (existingAddress != null && existingAddress.getUser().getUsername().equals(username)) {
			existingAddress.setStreet(address.getStreet());
			existingAddress.setCity(address.getCity());
			existingAddress.setState(address.getState());
			existingAddress.setZipCode(address.getZipCode());
			return addressRepo.saveAndFlush(existingAddress);
		}
		return null;
	}

	@Override
	public boolean delete(String username, int addressId) {
		Address address = addressRepo.findById(addressId).orElse(null);
		if (address != null && address.getUser().getUsername().equals(username)) {
			User user = userRepo.findByUsername(username);
			user.setAddress(null);
			userRepo.saveAndFlush(user);
			addressRepo.delete(address);
			return true;
		}
		return false;
	}

	@Override
	public Address create(int businessId, Address address) {
		Business business = businessRepo.findById(businessId);
		if (business != null) {
			address.setBusiness(business);
			business.setAddress(address);
			return addressRepo.saveAndFlush(address);
		}
		return null;
	}

	@Override
	public Address update(int businessId, int addressId, Address address) {
		Address existingAddress = addressRepo.findById(addressId).orElse(null);
		if (existingAddress != null && (existingAddress.getBusiness().getId() == (businessId))) {
			existingAddress.setStreet(address.getStreet());
			existingAddress.setCity(address.getCity());
			existingAddress.setState(address.getState());
			existingAddress.setZipCode(address.getZipCode());
			return addressRepo.saveAndFlush(existingAddress);
		}
		return null;
	}

	@Override
	public boolean delete(int businessId, int addressId) {
		Address address = addressRepo.findById(addressId).orElse(null);
		if (address != null && (address.getBusiness().getId() == (businessId))) {
			Business business = businessRepo.findById(businessId);
			business.setAddress(null);
			businessRepo.saveAndFlush(business);
			addressRepo.delete(address);
			return true;
		}
		return false;
	}
}
