package com.skilldistillery.pawrentsplace.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.pawrentsplace.entities.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer>  {
	
	Pet findByUser_UsernameAndId(String username, int petId);
	Set<Pet> findByUser_Username(String username);
	
	

	
}
