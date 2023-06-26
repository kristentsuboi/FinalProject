package com.skilldistillery.pawrentsplace.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.pawrentsplace.entities.BusinessRating;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusinessRating create(String username, int businessId, BusinessRating businessRating) {
		// TODO Auto-generated method stub
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
