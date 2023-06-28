package com.skilldistillery.pawrentsplace.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.pawrentsplace.entities.Business;
import com.skilldistillery.pawrentsplace.entities.Pet;
import com.skilldistillery.pawrentsplace.entities.User;
import com.skilldistillery.pawrentsplace.repositories.BusinessRepository;
import com.skilldistillery.pawrentsplace.repositories.PetRepository;
import com.skilldistillery.pawrentsplace.repositories.UserRepository;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	private BusinessRepository businessRepo;

	@Autowired
	private UserRepository userRepo;
	

	@Autowired
	private PetRepository petRepo;

	@Override
	public Business findById(int id) {
		return businessRepo.findById(id);
	}

	@Override
	public List<Business> findAll() {
		return businessRepo.findAll();
	}

	@Override
	public Business create(Business business) {
		return businessRepo.saveAndFlush(business);
	}

	@Override
	public Business update(int id, Business business) {
		Business existingBusiness = findById(id);
		if (existingBusiness != null) {
			existingBusiness.setName(business.getName());
			existingBusiness.setAbout(business.getAbout());
			existingBusiness.setPhone(business.getPhone());
			existingBusiness.setImageUrl(business.getImageUrl());
			return businessRepo.saveAndFlush(existingBusiness);
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		Business existingBusiness = findById(id);
		if (existingBusiness != null) {
			businessRepo.delete(existingBusiness);
			return true;
		}
		return false;
	}

	@Override
	public List<Business> findByServiceTypeId(int serviceType) {
		return businessRepo.findByServiceTypesId(serviceType);
	}

	@Override
	public boolean addClient(int businessId, int userId, String username) {
		Business business = businessRepo.findById(businessId);
		User user = userRepo.findById(userId);
		if ((business != null) && (user != null) && (username.equals(user.getUsername()))) {
			business.addClient(user);
			user.addBusinessUsed(business);
			for (User employee : business.getEmployees()) {
				for (Pet pet : user.getPets()) {
					employee.addPetClient(pet);
					pet.addProvider(employee);
					userRepo.saveAndFlush(employee);
					petRepo.saveAndFlush(pet);					
				}
			}
			businessRepo.saveAndFlush(business);
			userRepo.saveAndFlush(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeClient(int businessId, int userId, String username) {
		Business business = businessRepo.findById(businessId);
		User user = userRepo.findById(userId);
		if ((business != null) && (user != null) && (username.equals(user.getUsername()))) {
			business.removeClient(user);
			user.removeBusinessUsed(business);
			businessRepo.saveAndFlush(business);
			userRepo.saveAndFlush(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean addEmployee(int businessId, int userId, String username) {
		User user = userRepo.findById(userId);
		Business business = businessRepo.findById(businessId);
		if (business != null && user != null) {
			user.setBusiness(business);
			business.addEmployee(user);
			userRepo.saveAndFlush(user);
			businessRepo.saveAndFlush(business);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeEmployee(int businessId, int userId, String username) {
		User user = userRepo.findById(userId);
		Business business = businessRepo.findById(businessId);
		if (business != null && user != null) {
			user.setBusiness(null);
			business.removeEmployee(user);
			userRepo.saveAndFlush(user);
			businessRepo.saveAndFlush(business);
			return true;
		}
		return false;
	}

//    @Override
//    public List<ServiceType> findByServiceTypeId(int serviceType) {
//        return businessRepo.findByServiceTypeId(serviceType);
//    }
}
