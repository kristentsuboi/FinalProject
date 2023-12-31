package com.skilldistillery.pawrentsplace.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.pawrentsplace.entities.User;
import com.skilldistillery.pawrentsplace.services.AuthService;


@RestController
@CrossOrigin({"*", "http://localhost/"})
public class AuthController {
	@Autowired
	private AuthService authService;
	
	@PostMapping("register")
	public User register(@RequestBody User user, HttpServletResponse res) {
	  if (user == null) {
	     res.setStatus(400);
	     return null;
	  }
	  user = authService.register(user);
	  return user;
	}
	 
	@PutMapping("account/{userId}")
	public User update(@PathVariable int userId, @RequestBody User user, HttpServletResponse res) {
	  if (user == null) {
	     res.setStatus(400);
	     return null;
	  }
	  user = authService.update(userId, user);
	  if (user == null) {
		     res.setStatus(400);
		     return null;
		  }
	  return user;
	}
	
	@PutMapping("disable/{userId}")
	public void disable(@PathVariable int userId, HttpServletResponse res) {
		boolean deleted = authService.disable(userId);
		  if (deleted) {
		    res.setStatus(204);
		   }
		   else {
		    res.setStatus(404);
		   }
	}
	
	@GetMapping("authenticate")
	public User authenticate(Principal principal, HttpServletResponse res) {
	  if (principal == null) { // no Authorization header sent
	     res.setStatus(401);
	     res.setHeader("WWW-Authenticate", "Basic");
	     return null;
	  }
	  return authService.getUserByUsername(principal.getName());
	}
	
	

}
