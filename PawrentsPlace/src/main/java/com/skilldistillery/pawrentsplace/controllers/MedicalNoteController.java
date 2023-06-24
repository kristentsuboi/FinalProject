package com.skilldistillery.pawrentsplace.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.skilldistillery.pawrentsplace.entities.Diet;
import com.skilldistillery.pawrentsplace.entities.MedicalNote;
import com.skilldistillery.pawrentsplace.services.MedicalNoteService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class MedicalNoteController {

    @Autowired
    private MedicalNoteService medicalNoteService;
    
    @GetMapping("pets/{petId}/medicalNotes")
	public List<MedicalNote> index(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId, Principal principal) {
		List<MedicalNote> medicalNotes = medicalNoteService.index(principal.getName(), petId);
		return medicalNotes;
	}
    

    @GetMapping("medicalNotes/{id}")
    public MedicalNote show(HttpServletRequest req, 
    		HttpServletResponse res, 
    		Principal principal,
    		@PathVariable int id) {
        return medicalNoteService.show(principal.getName(), id);
    }

    @PostMapping("pets/{petId}/medicalNotes")
    public MedicalNote create(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId, @RequestBody MedicalNote medicalNote, Principal principal) {
        try {
            return medicalNoteService.create(principal.getName(), petId, medicalNote);
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(400);
            medicalNote = null;
        }
        return medicalNote;
    }
    
//    @PostMapping("medicalNotes")
//    public MedicalNote create(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody MedicalNote medicalNote) {
//        try {
//            return medicalNoteService.create(principal.getName(), medicalNote);
//        } catch (Exception e) {
//            e.printStackTrace();
//            res.setStatus(400);
//            medicalNote = null;
//        }
//        return medicalNote;
//    }

    @PutMapping("pets/{petId}/medicalNotes/{noteId}")
    public MedicalNote update(HttpServletRequest req, 
    		HttpServletResponse res, 
    		@PathVariable int petId, 
    		@PathVariable int noteId,
            @RequestBody MedicalNote medicalNote,
            Principal principal) {
        return medicalNoteService.update(principal.getName(), petId, noteId, medicalNote);
    }
    
//    @PutMapping("medicalNotes/{id}")
//    public MedicalNote update(HttpServletRequest req, 
//    		HttpServletResponse res, 
//    		Principal principal, 
//    		@PathVariable int id,
//            @RequestBody MedicalNote medicalNote) {
//        return medicalNoteService.update(principal.getName(), id, medicalNote);
//    }
    
    @DeleteMapping("pets/{petId}/medicalNotes/{noteId}")
    public void destroy(HttpServletRequest req, 
    		HttpServletResponse res, 
    		@PathVariable int petId,
            @PathVariable int noteId,
            Principal principal) {
        medicalNoteService.delete(principal.getName(), petId, noteId);
    }

//    @DeleteMapping("medicalNotes/{id}")
//    public void destroy(HttpServletRequest req, 
//    		HttpServletResponse res, 
//    		Principal principal,
//            @PathVariable int id) {
//        medicalNoteService.delete(principal.getName(), id);
//    }
    
}
