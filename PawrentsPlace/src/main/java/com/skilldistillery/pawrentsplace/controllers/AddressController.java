package com.skilldistillery.pawrentsplace.controllers;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.skilldistillery.pawrentsplace.entities.Address;
import com.skilldistillery.pawrentsplace.services.AddressService;


@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class AddressController {

    @Autowired
    private AddressService addressService;


    @GetMapping("account/{userId}/address")
    public Address show(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId, Principal principal) {
        return addressService.show(principal.getName(), userId);
    }

    @PostMapping("account/{userId}/address")
    public Address createForUser(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId, @RequestBody Address address, Principal principal) {
        try {
            return addressService.create(principal.getName(), address);
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(400);
            address = null;
        }
        return address;
    }

    @PutMapping("account/{userId}/address/{addressId}")
    public Address updateForUser(HttpServletRequest req, 
    		HttpServletResponse res, 
    		Principal principal, 
    		@PathVariable int userId,
    		@PathVariable int addressId,
            @RequestBody Address address) {
        return addressService.update(principal.getName(), addressId, address);
    }

    @DeleteMapping("account/{userId}/address/{addressId}")
    public void destroyForUser(HttpServletRequest req, 
    		HttpServletResponse res, 
    		Principal principal,
    		@PathVariable int userId,
    		@PathVariable int addressId) {
        addressService.delete(principal.getName(), addressId);
    }
    
    @PostMapping("business/{businessId}/address")
    public Address createForBusiness(HttpServletRequest req, HttpServletResponse res, @PathVariable int businessId, @RequestBody Address address, Principal principal) {
        try {
            return addressService.create(principal.getName(), address);
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(400);
            address = null;
        }
        return address;
    }

    @PutMapping("business/{businessId}/address/{addressId}")
    public Address updateForBusiness(HttpServletRequest req, 
    		HttpServletResponse res, 
    		Principal principal, 
    		@PathVariable int businessId,
    		@PathVariable int addressId,
            @RequestBody Address address) {
        return addressService.update(principal.getName(), addressId, address);
    }

    @DeleteMapping("business/{businessId}/address/{addressId}")
    public void destroyForBusiness(HttpServletRequest req, 
    		HttpServletResponse res, 
    		Principal principal,
    		@PathVariable int businessId,
    		@PathVariable int addressId) {
        addressService.delete(principal.getName(), addressId);
    }
    
    
}
