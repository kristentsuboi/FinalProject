package com.skilldistillery.pawrentsplace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.pawrentsplace.entities.Business;
import com.skilldistillery.pawrentsplace.entities.ServiceType;
import com.skilldistillery.pawrentsplace.entities.User;
import java.util.List;

public interface BusinessRepository extends JpaRepository<Business, Integer> {
	
    List<Business> findByIdAndEmployees(int businessId, User employeeId);
    List<Business> findByServiceTypesId(int serviceTypeId);
//    List<ServiceType> findByServiceTypeId(int serviceTypeId);
    
}
