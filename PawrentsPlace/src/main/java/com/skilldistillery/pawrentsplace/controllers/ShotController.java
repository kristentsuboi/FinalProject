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

import com.skilldistillery.pawrentsplace.entities.Medication;
import com.skilldistillery.pawrentsplace.entities.Shot;
import com.skilldistillery.pawrentsplace.services.PetService;
import com.skilldistillery.pawrentsplace.services.ShotService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class ShotController {
	@Autowired
	private PetService petService;
	@Autowired
	private ShotService shotService;

	@GetMapping("pets/{petId}/shots")
	public List<Shot> index(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId,
			Principal principal) {
		List<Shot> shots = shotService.index(principal.getName(), petId);
		return shots;
	}

	@GetMapping("pets/{petId}/shots/{shotId}")
	public Shot show(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId, @PathVariable int shotId,
			Principal principal) {
		return shotService.show(principal.getName(), shotId);
	}

	@PostMapping("pets/{petId}/shots")
	public Shot create(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId, @RequestBody Shot shot,
			Principal principal) {
		try {
			shot = shotService.create(principal.getName(), petId, shot);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(shot.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			shot = null;
		}
		return shot;
	}

	@PutMapping("pets/{petId}/shots/{shotId}")
	public Shot update(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId, @RequestBody Shot shot,
			@PathVariable int shotId, Principal principal) {
		Shot updated = null;
		try {
			updated = shotService.update(principal.getName(), shotId, shot, petId);
			if (updated == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return updated;
	}

	@DeleteMapping("pets/{petId}/shots/{shotId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int petId,
			@PathVariable int shotId, Principal principal) {
		boolean deleted = shotService.delete(principal.getName(), shotId, petId);
		if (deleted) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	}

}
