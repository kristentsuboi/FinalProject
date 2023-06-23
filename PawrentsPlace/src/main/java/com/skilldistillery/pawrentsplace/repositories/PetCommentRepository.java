package com.skilldistillery.pawrentsplace.repositories;

import java.util.List;

import com.skilldistillery.pawrentsplace.entities.PetComment;

public interface PetCommentRepository {
	List<PetComment>findByPet_Id(int petId);
	PetComment findById(int petCommentId);

}
