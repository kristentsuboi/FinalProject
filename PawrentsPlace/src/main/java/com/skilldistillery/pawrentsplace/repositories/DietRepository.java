package com.skilldistillery.pawrentsplace.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skilldistillery.pawrentsplace.entities.Diet;





public interface DietRepository extends JpaRepository<Diet, Integer>{
	List<Diet>findByPet_Id(int petId);
	//Diet findByUser_UsernameAndId(String username, int dietId);
	//Set<Diet> findByUser_Username(String username);
	

}
