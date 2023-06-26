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

import com.skilldistillery.pawrentsplace.entities.BusinessRating;
import com.skilldistillery.pawrentsplace.services.BusinessRatingService;


@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class BusinessRatingController {
	
	@Autowired
	private BusinessRatingService businessRatingService;
	
	
	
	
	@GetMapping("business/{businessId}/ratings")
	public List<BusinessRating> index(HttpServletRequest req, HttpServletResponse res, @PathVariable int businessId, Principal principal) {
		List<BusinessRating> businessRatings = businessRatingService.index(businessId);
		return businessRatings;
	}

	
	@PostMapping("business/{businessId}/ratings") 
	public BusinessRating create(HttpServletRequest req, HttpServletResponse res, @PathVariable int businessId, @RequestBody BusinessRating businessRating, Principal principal) {
		  try {
			   businessRating = businessRatingService.create(principal.getName(), businessId, businessRating);
			   res.setStatus(201);
			   StringBuffer url = req.getRequestURL();
			   url.append("/").append(businessRating.getId());
			   res.setHeader("Location", url.toString());
			  } catch (Exception e) {
			   e.printStackTrace();
			   res.setStatus(400);
			   businessRating = null;
			  }
			  return businessRating;
		
	}
	
	@PutMapping("business/{businessId}/businessRatings/{businessRatingId}")
	public BusinessRating update(HttpServletRequest req, HttpServletResponse res, @PathVariable int businessRatingId, @RequestBody BusinessRating businessRating, Principal principal) {
		 BusinessRating updated = null;;
		  try {
		   updated = businessRatingService.update(principal.getName(), businessRatingId, businessRating);
		   if (updated == null) {
		    res.setStatus(404);
		   }
		  } catch (Exception e) {
		   e.printStackTrace();
		   res.setStatus(400);
		  }
		  return updated;	
	}
	
	@DeleteMapping("business/{businessId}/businessRatings/{businessRatingId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int businessRatingId, Principal principal) {
		   boolean deleted = businessRatingService.delete(principal.getName(), businessRatingId);
		   if (deleted) {
		    res.setStatus(204);
		   }
		   else {
		    res.setStatus(404);
		   }
		  }
	
	
	
	
	
	

}
