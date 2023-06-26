package com.skilldistillery.pawrentsplace.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skilldistillery.pawrentsplace.entities.ServiceType;
import com.skilldistillery.pawrentsplace.repositories.ServiceTypeRepository;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService {

	@Autowired
	private ServiceTypeRepository serviceTypeRepo;
	
	@Override
	public List<ServiceType> index() {
		return serviceTypeRepo.findAll();
	}
	
}
