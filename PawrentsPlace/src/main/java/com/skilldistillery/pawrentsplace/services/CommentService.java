package com.skilldistillery.pawrentsplace.services;

import java.util.List;

import com.skilldistillery.pawrentsplace.entities.Comment;

public interface CommentService {
	List<Comment> index(String username, int userId);
	Comment show(String username, int commentId);
	Comment create(String username, int userId, Comment comment);
	Comment update(String username, int commentId, Comment comment, int userId);
	boolean delete(String username, int commentId, int userId);
//	List<Comment> showReplies(String username, int commentId, int userId);

}
