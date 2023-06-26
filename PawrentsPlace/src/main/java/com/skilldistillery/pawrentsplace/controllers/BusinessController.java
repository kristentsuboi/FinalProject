package com.skilldistillery.pawrentsplace.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.skilldistillery.pawrentsplace.entities.Business;
import com.skilldistillery.pawrentsplace.entities.ServiceType;
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
