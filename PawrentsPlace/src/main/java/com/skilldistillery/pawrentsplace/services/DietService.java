package com.skilldistillery.pawrentsplace.services;

import java.util.List;

import com.skilldistillery.pawrentsplace.entities.Diet;


public interface DietService {
	
	public List<Diet> index(String username, int dietId);
	public Diet show (String username, int dietId);
	public Diet create(String username, int petId, Diet diet);
	public Diet update(String username, int dietId, Diet diet, int petId);
	public boolean delete(String username, int dietId, int petId); 

}
