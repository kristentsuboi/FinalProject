package com.skilldistillery.pawrentsplace.services;

import java.util.List;

import com.skilldistillery.pawrentsplace.entities.Medication;

public interface MedicationService {
	List<Medication> index(String username, int petId);
	Medication show(String username, int medId);
	Medication create(String username, int petId, Medication medication);
	Medication update(String username, int medId, Medication medication, int petId);
	boolean delete(String username, int medId, int petId);

}
