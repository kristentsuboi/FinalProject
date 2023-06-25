package com.skilldistillery.pawrentsplace.services;

import java.util.List;

import com.skilldistillery.pawrentsplace.entities.PetComment;


public interface PetCommentService {
	List<PetComment> index(String username, int petId);
	PetComment show(String username, int petCommentId);
	PetComment create(String username, int petId, PetComment comment);
	PetComment update(String username, int petCommentId, PetComment comment, int petId);
	boolean delete(String username, int petCommentId, int petId);

}
