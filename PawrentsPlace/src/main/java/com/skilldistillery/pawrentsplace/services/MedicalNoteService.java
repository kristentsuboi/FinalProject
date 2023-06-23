package com.skilldistillery.pawrentsplace.services;

import java.util.List;

import com.skilldistillery.pawrentsplace.entities.MedicalNote;

public interface MedicalNoteService {


	MedicalNote show(String username, int medicalNoteId);
	MedicalNote create(String username, MedicalNote medicalNote);
	MedicalNote update(String username, int medicalNoteId, MedicalNote medicalNote);
	boolean delete(String username, int medicalNoteId);

    
}
