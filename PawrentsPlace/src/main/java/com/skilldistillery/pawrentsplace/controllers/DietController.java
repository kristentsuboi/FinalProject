package com.skilldistillery.pawrentsplace.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.pawrentsplace.entities.Diet;
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
	
	
	
	
	
	

}
