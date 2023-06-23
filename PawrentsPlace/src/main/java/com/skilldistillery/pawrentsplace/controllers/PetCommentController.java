package com.skilldistillery.pawrentsplace.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.pawrentsplace.entities.Medication;
import com.skilldistillery.pawrentsplace.entities.PetComment;
import com.skilldistillery.pawrentsplace.services.PetCommentService;
import com.skilldistillery.pawrentsplace.services.PetService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class PetCommentController {
	
	@Autowired
	private PetService petService;
	
	@Autowired 
	private PetCommentService petComService;
	

	@GetMapping("pets/{petId}/petcomments")
	public List<PetComment> index(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId,
			Principal principal) {
		List<PetComment> petComments = petComService.index(principal.getName(), petId);
		return petComments;
	}
	

	@GetMapping("pets/{petId}/petcomments/{petCommentId}")
	public PetComment show(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId,
			@PathVariable int petCommentId, Principal principal) {
		return petComService.show(principal.getName(), petCommentId);
	}
	
	@PostMapping("pets/{petId}/petcomments")
	public PetComment create(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId,
			@RequestBody PetComment petComment, Principal principal) {
		try {
			petComment = petComService.create(principal.getName(), petId, petComment);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(petComment.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			petComment = null;
		}
		return petComment;
	}
	
	
	
	

}
