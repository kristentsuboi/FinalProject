package com.skilldistillery.pawrentsplace.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.pawrentsplace.entities.Address;
import com.skilldistillery.pawrentsplace.entities.User;
import com.skilldistillery.pawrentsplace.repositories.AddressRepository;
import com.skilldistillery.pawrentsplace.repositories.UserRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private UserRepository userRepo;


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

}
