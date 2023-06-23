package com.skilldistillery.pawrentsplace.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.pawrentsplace.entities.Diet;
import com.skilldistillery.pawrentsplace.entities.Medication;
import com.skilldistillery.pawrentsplace.services.MedicationService;
import com.skilldistillery.pawrentsplace.services.PetService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class MedicationController {
	@Autowired
	private PetService petService;
	@Autowired
	private MedicationService medService;
	
	@GetMapping("pets/{petId}/medications")
	public List<Medication> index(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId, Principal principal) {
		List<Medication> meds = medService.index(principal.getName(), petId);
		return meds;
	}

	@GetMapping("pets/{petId}/medications/{medId}")
	public Medication show(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId, @PathVariable int medId, Principal principal) {
		return medService.show(principal.getName(), medId);
	}
	@PostMapping("pets/{petId}/medications") 
	public Medication create(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId, @RequestBody Medication medication, Principal principal) {
		  try {
			   medication = medService.create(principal.getName(), petId, medication);
			   res.setStatus(201);
			   StringBuffer url = req.getRequestURL();
			   url.append("/").append(medication.getId());
			   res.setHeader("Location", url.toString());
			  } catch (Exception e) {
			   e.printStackTrace();
			   res.setStatus(400);
			   medication = null;
			  }
			  return medication;
	}
	@PutMapping("pets/{petId}/medications/{medId}")
	public Medication update(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId, @RequestBody Medication medication,@PathVariable int medId, Principal principal) {
		 Medication updated = null;
		  try {
		   updated = medService.update(principal.getName(), medId, medication, petId);
		   if (updated == null) {
		    res.setStatus(404);
		   }
		  } catch (Exception e) {
		   e.printStackTrace();
		   res.setStatus(400);
		  }
		  return updated;	
	}
	@DeleteMapping("pets/{petId}/medications/{medId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res,@PathVariable int petId, @PathVariable int medId, Principal principal) {
		   boolean deleted = medService.delete(principal.getName(), medId, petId);
		   if (deleted) {
		    res.setStatus(204);
		   }
		   else {
		    res.setStatus(404);
		   }
		  }
}
