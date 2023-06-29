package com.skilldistillery.pawrentsplace.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.pawrentsplace.entities.Comment;
import com.skilldistillery.pawrentsplace.entities.Pet;
import com.skilldistillery.pawrentsplace.entities.PetComment;
import com.skilldistillery.pawrentsplace.entities.User;
import com.skilldistillery.pawrentsplace.repositories.CommentRepository;
import com.skilldistillery.pawrentsplace.repositories.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private UserRepository userRepo;


	@Override
	public Comment show(String username, int commentId) {
		return commentRepo.findById(commentId);
	}

	@Override
	public Comment create(String username, Comment comment) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			comment.setUser(user);
			return commentRepo.saveAndFlush(comment);
		}

		return null;
	}

	@Override
	public Comment update(String username, int userId, Comment comment) {
		
		Comment managedComment = commentRepo.findByUser_UsernameAndId(username, userId);
		if (managedComment != null) {
			managedComment.setBody(comment.getBody());
			managedComment.setImageUrl(comment.getImageUrl());
			managedComment.setTopic(comment.getTopic());
			return commentRepo.saveAndFlush(managedComment);
		}
		return null;
	}

	@Override
	public boolean delete(String username, int tid) {
		Comment existingComment = commentRepo.findByUser_UsernameAndId(username, tid);
		if (existingComment != null) {
			
			for(Comment comment : existingComment.getReplies()) {
				commentRepo.delete(comment);
			}
			
			commentRepo.delete(existingComment);
			return true;
		}
		return false; 
	}

	@Override
	public List<Comment> getAllComments() {
		return commentRepo.findAll();
	}


}
