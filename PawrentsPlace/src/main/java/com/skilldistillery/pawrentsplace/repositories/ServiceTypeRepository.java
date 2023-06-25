package com.skilldistillery.pawrentsplace.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.pawrentsplace.entities.ServiceType;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer> {
	List<ServiceType> findById(int servId);

}
