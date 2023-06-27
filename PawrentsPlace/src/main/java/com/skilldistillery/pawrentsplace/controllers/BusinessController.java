package com.skilldistillery.pawrentsplace.controllers;

import java.security.Principal;
import java.util.List;

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

import com.skilldistillery.pawrentsplace.entities.Business;
import com.skilldistillery.pawrentsplace.services.BusinessService;

@RestController
@RequestMapping("api/")
@CrossOrigin({ "*", "http://localhost/" })
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    
    //ONLY LOGIN USERS ARE ABLE TO SEE THE BUSINESSES
    @GetMapping("all")
    public List<Business> getAllBusinesses() {
        return businessService.findAll();
    }

    @GetMapping("business/{id}")
    public Business getBusinessById(@PathVariable int id) {
        return businessService.findById(id);
    }
    
    @GetMapping("business/serviceType/{serviceTypeId}")
    public List<Business> getBusinessesByServiceTypeId(@PathVariable Integer serviceTypeId) {
        return businessService.findByServiceTypeId(serviceTypeId);
    }
    
    @GetMapping("business/{businessId}/add/{userId}")
    public void addBusinessClient(@PathVariable int businessId, @PathVariable int userId, Principal principal) {
        businessService.addClient(businessId, userId, principal.getName());
    }
    
    @DeleteMapping("business/{businessId}/remove/{userId}")
    public void removeBusinessClient(@PathVariable int businessId, @PathVariable int userId, Principal principal) {
        businessService.removeClient(businessId, userId, principal.getName());
    }

    @PostMapping("business")
    public Business createBusiness(@RequestBody Business business) {
        return businessService.create(business);
    }

    @PutMapping("business/{id}")
    public Business updateBusiness(@PathVariable int id, @RequestBody Business business) {
        return businessService.update(id, business);
    }
    
 
    @DeleteMapping("business/{id}")
    public void deleteBusiness(@PathVariable int id) {
        businessService.delete(id);
    }
   
}
