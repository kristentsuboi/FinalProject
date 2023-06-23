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
import com.skilldistillery.pawrentsplace.services.DietService;
import com.skilldistillery.pawrentsplace.services.PetService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class DietController {
	
	@Autowired
	private DietService dietService;
	
	@Autowired
	private PetService petService;
	
	
	
	@GetMapping("pets/{petId}/diets")
	public List<Diet> index(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId, Principal principal) {
		List<Diet> diets = dietService.index(principal.getName(), petId);
		return diets;
	}
	@GetMapping("pets/{petId}/diets/{dietId}")
	public Diet show(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId, @PathVariable int dietId, Principal principal) {
		return dietService.show(principal.getName(), dietId);
	}
	
	@PostMapping("pets/{petId}/diets") 
	public Diet create(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId, @RequestBody Diet diet, Principal principal) {
		  try {
			   diet = dietService.create(principal.getName(), petId, diet);
			   res.setStatus(201);
			   StringBuffer url = req.getRequestURL();
			   url.append("/").append(diet.getId());
			   res.setHeader("Location", url.toString());
			  } catch (Exception e) {
			   e.printStackTrace();
			   res.setStatus(400);
			   diet = null;
			  }
			  return diet;
		
	}
	
	@PutMapping("pets/{petId}/diets/{dietId}")
	public Diet update(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId, @RequestBody Diet diet,@PathVariable int dietId, Principal principal) {
		 Diet updated = null;;
		  try {
		   updated = dietService.update(principal.getName(), dietId, diet, petId);
		   if (updated == null) {
		    res.setStatus(404);
		   }
		  } catch (Exception e) {
		   e.printStackTrace();
		   res.setStatus(400);
		  }
		  return updated;	
	}
	
	@DeleteMapping("pets/{petId}/diets/{dietId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res,@PathVariable int petId, @PathVariable int dietId, Principal principal) {
		   boolean deleted = dietService.delete(principal.getName(), dietId, petId);
		   if (deleted) {
		    res.setStatus(204);
		   }
		   else {
		    res.setStatus(404);
		   }
		  }
	
	
	
	
	
	

}
