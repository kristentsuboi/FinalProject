package com.skilldistillery.pawrentsplace.services;

import java.util.Set;

import com.skilldistillery.pawrentsplace.entities.Pet;

public interface PetService {
	
public Set<Pet> index(String username);
public Pet show(String username, int petId);
public Pet create(String username, Pet pet);
public Pet update(String username, int petId, Pet pet);
public boolean delete(String username, int petId); 


}
