package com.skilldistillery.pawrentsplace.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.pawrentsplace.entities.Diet;
import com.skilldistillery.pawrentsplace.entities.MedicalNote;
import com.skilldistillery.pawrentsplace.entities.Pet;
import com.skilldistillery.pawrentsplace.repositories.MedicalNoteRepository;
import com.skilldistillery.pawrentsplace.repositories.PetRepository;
import com.skilldistillery.pawrentsplace.repositories.UserRepository;

@Service
public class MedicalNoteServiceImpl implements MedicalNoteService {

    @Autowired
    private MedicalNoteRepository medicalNoteRepository;
    
	@Autowired
	private PetRepository petRepo;
    
    @Autowired
	private UserRepository userRepo;
    
	@Override
	public List<MedicalNote> index(String username, int petId) {
		Pet pet = petRepo.findById(petId);
		if(pet != null) {
			return medicalNoteRepository.findByPet_Id(petId);
			
		}
	return null;
	}


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
    public MedicalNote create(String username, int petId, MedicalNote medicalNote) {
        // Set the user to the medicalNote
        medicalNote.setUser(userRepo.findByUsername(username));
        medicalNote.setPet(petRepo.findById(petId));
        return medicalNoteRepository.save(medicalNote);
    }

    @Override
    public MedicalNote update(String username, int petId, int medicalNoteId, MedicalNote medicalNote) {
        Optional<MedicalNote> optionalMedicalNote = medicalNoteRepository.findById(medicalNoteId);
        if (optionalMedicalNote.isPresent()) {
            MedicalNote existingMedicalNote = optionalMedicalNote.get();
            if (existingMedicalNote.getUser().getUsername().equals(username)) {
           
                existingMedicalNote.setNotes(medicalNote.getNotes());
//                existingMedicalNote.setUpdatedAt(medicalNote.getUpdatedAt());
                return medicalNoteRepository.save(existingMedicalNote);
            }
        }
        return null;
    }

    @Override
    public boolean delete(String username, int petId, int medicalNoteId) {
        Optional<MedicalNote> optionalMedicalNote = medicalNoteRepository.findById(medicalNoteId);
        if (optionalMedicalNote.isPresent()) {
            MedicalNote medicalNote = optionalMedicalNote.get();
            if (medicalNote.getUser().getUsername().equals(username)) {
            	medicalNote.setPet(null);
            	medicalNote.setUser(null);
                medicalNoteRepository.delete(medicalNote);
                return true;
            }
        }
        return false;
    }
}
