package com.skilldistillery.pawrentsplace.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.pawrentsplace.entities.MedicalNote;
import com.skilldistillery.pawrentsplace.repositories.MedicalNoteRepository;
import com.skilldistillery.pawrentsplace.repositories.UserRepository;

@Service
public class MedicalNoteServiceImpl implements MedicalNoteService {

    @Autowired
    private MedicalNoteRepository medicalNoteRepository;
    
    @Autowired
	private UserRepository userRepo;
    

    @Override
    public MedicalNote show(String username, int medicalNoteId) {
        Optional<MedicalNote> optionalMedicalNote = medicalNoteRepository.findById(medicalNoteId);
        if (optionalMedicalNote.isPresent()) {
            MedicalNote medicalNote = optionalMedicalNote.get();
            if (medicalNote.getUser().getUsername().equals(username)) {
                return medicalNote;
            }
        }
        return null;
    }


    @Override
    public MedicalNote create(String username, MedicalNote medicalNote) {
        // Set the user to the medicalNote
        medicalNote.setUser(userRepo.findByUsername(username));
        return medicalNoteRepository.save(medicalNote);
    }

    @Override
    public MedicalNote update(String username, int medicalNoteId, MedicalNote medicalNote) {
        Optional<MedicalNote> optionalMedicalNote = medicalNoteRepository.findById(medicalNoteId);
        if (optionalMedicalNote.isPresent()) {
            MedicalNote existingMedicalNote = optionalMedicalNote.get();
            if (existingMedicalNote.getUser().getUsername().equals(username)) {
           
                existingMedicalNote.setNotes(medicalNote.getNotes());
                existingMedicalNote.setUpdatedAt(medicalNote.getUpdatedAt());
      

                return medicalNoteRepository.save(existingMedicalNote);
            }
        }
        return null;
    }

    @Override
    public boolean delete(String username, int medicalNoteId) {
        Optional<MedicalNote> optionalMedicalNote = medicalNoteRepository.findById(medicalNoteId);
        if (optionalMedicalNote.isPresent()) {
            MedicalNote medicalNote = optionalMedicalNote.get();
            if (medicalNote.getUser().getUsername().equals(username)) {
                medicalNoteRepository.delete(medicalNote);
                return true;
            }
        }
        return false;
    }
}
