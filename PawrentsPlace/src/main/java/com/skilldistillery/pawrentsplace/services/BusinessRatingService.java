package com.skilldistillery.pawrentsplace.services;

import java.util.List;

import com.skilldistillery.pawrentsplace.entities.BusinessRating;


public interface BusinessRatingService {
	
	public List<BusinessRating> index(int businessId);
	public BusinessRating create(String username, int businessId, BusinessRating businessRating);
	public BusinessRating update(String username, int businessRatingId, BusinessRating businessRating);
	public boolean delete(String username, int businessRatingId); 

}
