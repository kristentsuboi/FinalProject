package com.skilldistillery.pawrentsplace.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.skilldistillery.pawrentsplace.entities.Medication;
import com.skilldistillery.pawrentsplace.entities.Pet;
import com.skilldistillery.pawrentsplace.repositories.MedicationRepository;
import com.skilldistillery.pawrentsplace.repositories.PetRepository;

@Service
public class MedicationServiceImpl implements MedicationService {
	@Autowired
	private MedicationRepository medRepo;
	@Autowired
	private PetRepository petRepo;

	@Override
	public List<Medication> index(String username, int petId) {
		Pet pet = petRepo.findById(petId);
		if (pet != null) {
			return medRepo.findByPet_Id(petId);

		}
		return null;
	}

	@Override
	public Medication show(String username, int medId) {
		return medRepo.findById(medId);
	}

	@Override
	public Medication create(String username, int petId, Medication medication) {
		Pet pet = petRepo.findByUser_UsernameAndId(username, petId);
		if (pet != null) {
			medication.setPet(pet);
			Medication managedMed = medRepo.saveAndFlush(medication);
			return managedMed;
		}

		return null;
	}

	@Override
	public Medication update(String username, int medId, Medication medication, int petId) {
		Pet managedPet = petRepo.findByUser_UsernameAndId(username, petId);
		Medication managedMed = medRepo.findById(medId);
		if (managedMed != null) {
			managedMed.setName(medication.getName());
			managedMed.setLastAdministered(medication.getLastAdministered());
			managedMed.setFrequency(medication.getFrequency());
			managedMed.setWithFood(medication.getWithFood());
			managedMed.setDateStarted(medication.getDateStarted());
			managedMed.setNotes(medication.getNotes());
			return medRepo.saveAndFlush(managedMed);
		}
		return null;
	}

	@Override
	public boolean delete(String username, int medId, int petId) {
		Pet pet = petRepo.findById(petId);
		Medication deleted = medRepo.findById(medId);
		if (deleted != null) {
			ArrayList<Medication> meds = new ArrayList<>(pet.getMedications());
			for (Medication med : meds) {
				if (med.getId() == medId) {
					pet.getMedications().remove(med);
				}
			}
			petRepo.saveAndFlush(pet);
			medRepo.delete(deleted);
			return true;
		} else {
			return false;
		}
	}

}
