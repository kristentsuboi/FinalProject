package com.skilldistillery.pawrentsplace.services;

import java.util.List;
import com.skilldistillery.pawrentsplace.entities.Business;
import com.skilldistillery.pawrentsplace.entities.ServiceType;
import com.skilldistillery.pawrentsplace.entities.User;

public interface BusinessService {
    Business findById(int id);
    List<Business> findAll();
    Business create(Business business);
    Business update(int id, Business business);
    boolean delete(int id);
    List<Business> findByServiceTypeId(int serviceType);
//    List<ServiceType> findByServiceTypeId(int serviceType);
}
