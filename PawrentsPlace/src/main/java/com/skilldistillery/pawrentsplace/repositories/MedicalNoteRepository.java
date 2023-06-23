package com.skilldistillery.pawrentsplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.pawrentsplace.entities.MedicalNote;


public interface MedicalNoteRepository extends JpaRepository<MedicalNote, Integer> {
	
	MedicalNote findByUserUsername(String username);

}
