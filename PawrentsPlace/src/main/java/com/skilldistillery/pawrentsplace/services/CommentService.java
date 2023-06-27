package com.skilldistillery.pawrentsplace.services;

import java.util.List;

import com.skilldistillery.pawrentsplace.entities.Comment;

public interface CommentService {

	List<Comment> getAllComments();
	Comment show(String username, int commentId);
	Comment create(String username, Comment comment);
	Comment update(String username, int commentId, Comment comment);
	boolean delete(String username, int commentId);


}
