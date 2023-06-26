package com.skilldistillery.pawrentsplace.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.pawrentsplace.entities.ServiceType;
import com.skilldistillery.pawrentsplace.services.ServiceTypeService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class ServiceTypeController {
	
	@Autowired
	private ServiceTypeService serviceTypeService;
	
	
	@GetMapping("services")
	public List<ServiceType> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		List<ServiceType> serviceTypes = serviceTypeService.index();
		return serviceTypes;
	}
	
	
	
	
	
	

}
