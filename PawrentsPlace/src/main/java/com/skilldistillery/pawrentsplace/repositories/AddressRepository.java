package com.skilldistillery.pawrentsplace.repositories;

import com.skilldistillery.pawrentsplace.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	Address findByUserUsername(String username);

}
