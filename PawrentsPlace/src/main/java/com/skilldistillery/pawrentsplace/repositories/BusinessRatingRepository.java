package com.skilldistillery.pawrentsplace.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.pawrentsplace.entities.BusinessRating;

public interface BusinessRatingRepository extends JpaRepository<BusinessRating, Integer>{
	List<BusinessRating>findByBusiness_Id(int businessId);
	List<BusinessRating>findByUser_Id(int userId);
	List<BusinessRating>findByBusiness_Id_And_User_Id(int businessId, int userId);
	BusinessRating findById(int businessId);
	
}
