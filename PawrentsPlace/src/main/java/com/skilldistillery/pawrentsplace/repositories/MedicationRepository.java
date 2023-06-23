package com.skilldistillery.pawrentsplace.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.pawrentsplace.entities.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
	List<Medication>findByPet_Id(int petId);
	Medication findById(int medId);

}
