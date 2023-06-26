package com.skilldistillery.pawrentsplace.services;

import java.util.Set;

import com.skilldistillery.pawrentsplace.entities.Address;
import com.skilldistillery.pawrentsplace.entities.Address;

public interface AddressService {

	public Address show(String username, int addressId);
	public Address create(String username, Address address);
	public Address update(String username, int addressId, Address address);
	public boolean delete(String username, int addressId); 
	public Address create(int businessId, Address address);
	public Address update(int businessId, int addressId, Address address);
	public boolean delete(int businessId, int addressId); 

}
