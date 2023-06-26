package com.skilldistillery.pawrentsplace.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.pawrentsplace.entities.Business;
import com.skilldistillery.pawrentsplace.entities.BusinessRating;
import com.skilldistillery.pawrentsplace.entities.BusinessRatingId;
import com.skilldistillery.pawrentsplace.entities.User;
import com.skilldistillery.pawrentsplace.repositories.BusinessRatingRepository;
import com.skilldistillery.pawrentsplace.repositories.BusinessRepository;
import com.skilldistillery.pawrentsplace.repositories.UserRepository;

@Service
public class BusinessRatingServiceImpl implements BusinessRatingService {
	
	private BusinessRatingRepository businessRatingRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BusinessRepository businessRepo;

	@Override
	public List<BusinessRating> index(int businessId) {
		Business business = businessRepo.findById(businessId);
		if(business != null) {
			return business.getBusinessRatings();
		};
		return null;
	}

	@Override
	public BusinessRating create(String username, int businessId, BusinessRating businessRating) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			BusinessRatingId ratingId = new BusinessRatingId(user.getId(), businessId);
			
		}
		return null;
	}

	@Override
	public BusinessRating update(String username, int businessRatingId, BusinessRating businessRating) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String username, int businessRatingId) {
		// TODO Auto-generated method stub
		return false;
	}

}
