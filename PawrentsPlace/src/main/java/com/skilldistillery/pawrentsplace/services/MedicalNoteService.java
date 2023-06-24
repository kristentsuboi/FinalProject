package com.skilldistillery.pawrentsplace.services;

import java.util.List;

import com.skilldistillery.pawrentsplace.entities.MedicalNote;

public interface MedicalNoteService {

	List<MedicalNote> index(String username, int petId);
	MedicalNote show(String username, int medicalNoteId);
	MedicalNote create(String username, int petId, MedicalNote medicalNote);
	MedicalNote update(String username, int petId, int medicalNoteId, MedicalNote medicalNote);
	boolean delete(String username, int petId, int medicalNoteId);

    
}
