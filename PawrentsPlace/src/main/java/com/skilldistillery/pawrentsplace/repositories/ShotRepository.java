package com.skilldistillery.pawrentsplace.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.pawrentsplace.entities.Shot;

public interface ShotRepository extends JpaRepository<Shot, Integer> {
	List<Shot>findByPet_Id(int petId);
	Shot findById(int shotId);

}
