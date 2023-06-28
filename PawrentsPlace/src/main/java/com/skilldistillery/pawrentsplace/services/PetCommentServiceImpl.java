package com.skilldistillery.pawrentsplace.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.pawrentsplace.entities.Pet;
import com.skilldistillery.pawrentsplace.entities.PetComment;
import com.skilldistillery.pawrentsplace.entities.User;
import com.skilldistillery.pawrentsplace.repositories.PetCommentRepository;
import com.skilldistillery.pawrentsplace.repositories.PetRepository;
import com.skilldistillery.pawrentsplace.repositories.UserRepository;


@Service 
public class PetCommentServiceImpl implements PetCommentService {
	
	@Autowired
	private PetCommentRepository petCommentRepo;
	@Autowired
	private PetRepository petRepo;
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<PetComment> index(String username, int petId) {
		Pet pet = petRepo.findById(petId);
		if (pet != null) {
			return petCommentRepo.findByPet_Id(petId);

		}
		return null;
	}

	@Override
	public PetComment show(String username, int petCommentId) {
		return petCommentRepo.findById(petCommentId);
	}

	@Override
	public PetComment create(String username, int petId, PetComment comment) {
		Pet pet = petRepo.findByUser_UsernameAndId(username, petId);
		User user = userRepo.findById(petId);
		if (pet != null && user != null) {
			comment.setPet(pet);
			comment.setUser(user);
			PetComment managedPetComment = petCommentRepo.saveAndFlush(comment);
			return managedPetComment;
		}

		return null;
	}

	@Override
	public PetComment update(String username, int petCommentId, PetComment comment, int petId) {
		Pet managedPet = petRepo.findByUser_UsernameAndId(username, petId);
		PetComment managedPetComment = petCommentRepo.findById(petCommentId);
		if (managedPetComment != null) {
			managedPetComment.setBody(comment.getBody());
			managedPetComment.setImageUrl(comment.getImageUrl());
			return petCommentRepo.saveAndFlush(managedPetComment);
		}
		return null;
	}

	@Override
	public boolean delete(String username, int petCommentId, int petId) {
		Pet pet = petRepo.findById(petId);
		PetComment deleted = petCommentRepo.findById(petCommentId);
		if (deleted != null) {
			ArrayList<PetComment> petComments = new ArrayList<>(pet.getPetComments());
			for (PetComment petComment : petComments) {
				if (petComment.getId() == petCommentId) {
					pet.getPetComments().remove(petComment);
				}
			}
			petRepo.saveAndFlush(pet);
			petCommentRepo.delete(deleted);
			return true;
		} else {
			return false;
		}
		
	}
	
	@Override
	public PetComment createReply(String username, int petId,int mainCommentId, PetComment commentReply) {
		Pet pet = petRepo.findByUser_UsernameAndId(username, petId);
		User user = userRepo.findById(petId);
		PetComment mainComment = petCommentRepo.findById(mainCommentId);
		if (pet != null && user != null && mainComment != null) {
		
			commentReply.setPet(pet);
			commentReply.setUser(user);
			commentReply.setMainComment(mainComment);
			mainComment.addReply(commentReply);
			petCommentRepo.saveAndFlush(mainComment);
			PetComment managedPetComment = petCommentRepo.saveAndFlush(commentReply);
			
			return managedPetComment;
		}

		return null;
	}

}
