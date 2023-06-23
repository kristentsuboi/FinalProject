package com.skilldistillery.pawrentsplace.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.pawrentsplace.entities.PetComment;

public interface PetCommentRepository extends JpaRepository<PetComment, Integer>{
	List<PetComment>findByPet_Id(int petId);
	PetComment findById(int petCommentId);

}
