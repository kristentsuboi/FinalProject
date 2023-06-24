package com.skilldistillery.pawrentsplace.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.pawrentsplace.entities.MedicalNote;


public interface MedicalNoteRepository extends JpaRepository<MedicalNote, Integer> {
	
	List<MedicalNote>findByPet_Id(int petId);
	MedicalNote findByUserUsername(String username);

}
