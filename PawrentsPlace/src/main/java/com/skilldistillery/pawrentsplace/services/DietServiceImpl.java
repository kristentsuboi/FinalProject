package com.skilldistillery.pawrentsplace.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.pawrentsplace.entities.Diet;
import com.skilldistillery.pawrentsplace.entities.Pet;
import com.skilldistillery.pawrentsplace.repositories.DietRepository;
import com.skilldistillery.pawrentsplace.repositories.PetRepository;


@Service
public class DietServiceImpl implements DietService{
	
	@Autowired
	private PetRepository petRepo;
	
	@Autowired
	private DietRepository dietRepo;
	
	
	
	
	
	

	@Override
	public List<Diet> index(String username, int petId) {
		Pet pet = petRepo.findById(petId);
		if(pet != null) {
			return dietRepo.findByPet_Id(petId);
			
		}
	return null;
	}


	@Override
	public Diet create(String username, int petId, Diet diet) {
		Pet pet = petRepo.findByUser_UsernameAndId(username, petId);
		if(pet != null) {
			diet.setPet(pet);
			Diet managedDiet = dietRepo.saveAndFlush(diet);
			return managedDiet;
		}
		
		return null;
	}
//
//	@Override
//	public Diet update(String username, int dietId, Diet diet, int petId) {
//		Pet managedPet = petRepo.findByUser_UsernameAndId(username, petId);
//		Diet managedDiet = dietRepo.findByUser_UsernameAndId(username, dietId);
//		if (managedDiet != null) {
//			managedDiet.setName(diet.getName());
//			managedDiet.setType(diet.getType());
//			managedDiet.setFrequency(diet.getFrequency());
//			managedDiet.setNotes(diet.getNotes());
//			managedDiet.setAmount(diet.getAmount());
//			return dietRepo.saveAndFlush(managedDiet);
//		}
//		return null;
//	}

	@Override
	public Diet show(String username, int dietId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Diet update(String username, int dietId, Diet diet, int petId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String username, int dietId) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public boolean delete(String username, int dietId) {
//	Diet deleted = dietRepo.findByUser_UsernameAndId(username, dietId);
//	if(deleted != null) {
//		dietRepo.delete(deleted);
//		return true;
//	}else {
//		return false;
//	}
//	}

}
