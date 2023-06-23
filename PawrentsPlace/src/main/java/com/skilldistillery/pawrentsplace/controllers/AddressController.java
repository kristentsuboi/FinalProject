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


    @GetMapping("address/{id}")
    public Address show(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable int id) {
        return addressService.show(principal.getName(), id);
    }

    @PostMapping("address")
    public Address create(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody Address address) {
        try {
            return addressService.create(principal.getName(), address);
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(400);
            address = null;
        }
        return address;
    }

    @PutMapping("address/{id}")
    public Address update(HttpServletRequest req, 
    		HttpServletResponse res, 
    		Principal principal, 
    		@PathVariable int id,
            @RequestBody Address address) {
        return addressService.update(principal.getName(), id, address);
    }

    @DeleteMapping("address/{id}")
    public void destroy(HttpServletRequest req, 
    		HttpServletResponse res, 
    		Principal principal,
            @PathVariable int id) {
        addressService.delete(principal.getName(), id);
    }
}
