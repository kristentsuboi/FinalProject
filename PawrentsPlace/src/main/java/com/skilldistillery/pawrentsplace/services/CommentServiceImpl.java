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

//	@Override
//	public List<Comment> index(String username, int userId) {
//		User user = userRepo.findById(userId);
//		if (user != null) {
//			return commentRepo.findByUser_Id(userId);
//
//		}
//		return null;
//	}
	
	

	@Override
	public Comment show(String username, int commentId) {
		return commentRepo.findById(commentId);
	}

	@Override
	public Comment create(String username, int userId, Comment comment) {
		User user = userRepo.findById(userId);
		if (user != null) {
			comment.setUser(user);
			Comment managedComment = commentRepo.saveAndFlush(comment);
			return managedComment;
		}

		return null;
	}

	@Override
	public Comment update(String username, int userId, Comment comment, int commentId) {
		User managedUser = userRepo.findByUsername(username);
		Comment managedComment = commentRepo.findById(commentId);
		if (managedComment != null) {
			managedComment.setBody(comment.getBody());
			managedComment.setImageUrl(comment.getImageUrl());
			managedComment.setTopic(comment.getTopic());
			return commentRepo.saveAndFlush(managedComment);
		}
		return null;
	}

	@Override
	public boolean delete(String username, int commentId, int userId) {
		User user = userRepo.findById(userId);
		Comment deleted = commentRepo.findById(commentId);
		if (deleted != null) {
			ArrayList<Comment> comments = new ArrayList<>(user.getComments());
			for (Comment comment : comments) {
				if (comment.getId() == commentId) {
					user.getComments().remove(comment);
				}
			}
			userRepo.saveAndFlush(user);
			commentRepo.delete(deleted);
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public List<Comment> getAllComments() {
		return commentRepo.findAll();
	}

//	@Override
//	public List<Comment> showReplies(String username, int commentId, int userId) {
//		List<Comment> allComments = commentRepo.findByUser_Id(userId);
//		List<Comment> replies = null;
//		for (Comment comment: allComments) {
//			if (comment.getReplies() != null) {
//				replies.add(comment);
//				return replies;
//			}
//		}
//		
//		return null;
//	}

}
