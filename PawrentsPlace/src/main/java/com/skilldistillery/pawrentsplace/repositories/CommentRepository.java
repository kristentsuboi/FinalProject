package com.skilldistillery.pawrentsplace.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.pawrentsplace.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	List<Comment> findByUser_Id(int userId);
	Comment findById(int commentId);
	

}
