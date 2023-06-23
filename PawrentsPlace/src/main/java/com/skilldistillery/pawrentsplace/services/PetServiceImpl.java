 package com.skilldistillery.pawrentsplace.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.pawrentsplace.entities.Pet;
import com.skilldistillery.pawrentsplace.entities.User;
import com.skilldistillery.pawrentsplace.repositories.PetRepository;
import com.skilldistillery.pawrentsplace.repositories.UserRepository;
import com.skilldistillery.pawrentsplace.services.PetService;

@Service
public class PetServiceImpl implements PetService {

	@Autowired
	private PetRepository petRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public Set<Pet> index(String username) {
		return petRepo.findByUser_Username(username);

	}

	@Override
	public Pet show(String username, int petId) {
		return petRepo.findByUser_UsernameAndId(username, petId);
	}

	@Override
	public Pet create(String username, Pet pet) {
		User managedUser = userRepo.findByUsername(username);
		if (managedUser != null) {
			pet.setUser(managedUser);
			Pet managedPet = petRepo.saveAndFlush(pet);
			return managedPet;

		}
		return null;
	}

	@Override
	public Pet update(String username, int petId, Pet pet) {
		User managedUser = userRepo.findByUsername(username);
		Pet managedPet = petRepo.findByUser_UsernameAndId(username, petId);
		if (managedPet != null) {
			managedPet.setName(pet.getName());
			managedPet.setSpecies(pet.getSpecies());
			managedPet.setBreed(pet.getBreed());
			managedPet.setHeight(pet.getHeight());
			managedPet.setWeight(pet.getWeight());
			managedPet.setGender(pet.getGender());
			managedPet.setBirth(pet.getBirth());
			managedPet.setColor(pet.getColor());
			managedPet.setAbout(pet.getAbout());
			managedPet.setMicrochipped(pet.isMicrochipped());
			managedPet.setImageUrl(pet.getImageUrl());
			managedPet.setAllergies(pet.getAllergies());
			return petRepo.saveAndFlush(managedPet);

		}

		return null;
	}

	@Override
	public boolean delete(String username, int petId) {
		Pet deleted = petRepo.findByUser_UsernameAndId(username, petId);
		if (deleted != null) {
			petRepo.delete(deleted);
			return true;
		} else {
			return false;
		}
	}
}