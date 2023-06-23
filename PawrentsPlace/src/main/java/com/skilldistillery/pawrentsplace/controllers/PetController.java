package com.skilldistillery.pawrentsplace.controllers;

import java.security.Principal;
import java.util.Set;

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

import com.skilldistillery.pawrentsplace.entities.Pet;
import com.skilldistillery.pawrentsplace.services.PetService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class PetController {

	@Autowired
	private PetService petService;

	@GetMapping("pets")
	 public Set<Pet> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		
	  Set<Pet> pets = petService.index(principal.getName());
	  return pets;
	}
	
	@GetMapping("pets/{petId}")
	 public Pet show(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId, Principal principal) {
	  Pet pet = petService.show(principal.getName(), petId);
	  if (pet == null) {
	   res.setStatus(404);
	  }
	  return pet;
	 }
	
	@PostMapping("pets")
	 public Pet create(HttpServletRequest req, HttpServletResponse res, @RequestBody Pet pet, Principal principal) {
	  try {
	   pet = petService.create(principal.getName(), pet);
	   res.setStatus(201);
	   StringBuffer url = req.getRequestURL();
	   url.append("/").append(pet.getId());
	   res.setHeader("Location", url.toString());
	  } catch (Exception e) {
	   e.printStackTrace();
	   res.setStatus(400);
	   pet = null;
	  }
	  return pet;
	 }
	
	@PutMapping("pets/{petId}")
	 public Pet update(HttpServletRequest req, HttpServletResponse res,@PathVariable int petId, @RequestBody Pet pet, Principal principal) {
	  Pet updated = null;;
	  try {
	   updated = petService.update(principal.getName(), petId, pet);
	   if (updated == null) {
	    res.setStatus(404);
	   }
	  } catch (Exception e) {
	   e.printStackTrace();
	   res.setStatus(400);
	  }
	  return updated;
	 }
	
	
	@DeleteMapping("pets/{petId}")
	 public void destroy(HttpServletRequest req, HttpServletResponse res,@PathVariable int petId, Principal principal) {
	  boolean deleted = petService.delete(principal.getName(), petId);
	  if (deleted) {
	   res.setStatus(204);
	  }
	  else {
	   res.setStatus(404);
	  }
	 }

}
