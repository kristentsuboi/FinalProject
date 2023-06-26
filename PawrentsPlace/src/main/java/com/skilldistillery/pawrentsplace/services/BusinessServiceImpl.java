package com.skilldistillery.pawrentsplace.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.pawrentsplace.entities.Business;
import com.skilldistillery.pawrentsplace.entities.User;
import com.skilldistillery.pawrentsplace.repositories.BusinessRepository;
import com.skilldistillery.pawrentsplace.repositories.UserRepository;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessRepository businessRepo;
    
    @Autowired
    private UserRepository userRepo;

    @Override
    public Business findById(int id) {
        return businessRepo.findById(id).orElse(null);
    }

    @Override
    public List<Business> findAll() {
        return businessRepo.findAll();
    }


    @Override
    public Business create(Business business) {
        return businessRepo.save(business);
    }

    @Override
    public Business update(int id, Business business) {
        Business existingBusiness = findById(id);
        if (existingBusiness != null) {
            business.setId(id);
            return businessRepo.save(business);
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
	public List<Business> findByServiceType(List<Business> service) {
		List<Business> businesses = businessRepo.findByServiceTypes(service);
		return businesses;
		
	}
}
