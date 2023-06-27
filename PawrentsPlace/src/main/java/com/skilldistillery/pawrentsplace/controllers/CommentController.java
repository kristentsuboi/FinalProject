package com.skilldistillery.pawrentsplace.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.pawrentsplace.entities.Comment;
import com.skilldistillery.pawrentsplace.entities.PetComment;
import com.skilldistillery.pawrentsplace.services.CommentService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
//	@GetMapping("account/{userId}/comments")
//	public List<Comment> index(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId,
//			Principal principal) {
//		List<Comment> comments = commentService.index(principal.getName(), userId);
//		return comments;
//	}
	
//	@GetMapping("account/comments")
//	public List<Comment> index(HttpServletRequest req, HttpServletResponse res, @PathVariable
//			Principal principal) {
//		List<Comment> comments = commentService.findAll();
//		return comments;
//	}
	
	 @GetMapping("account/comments")
	    public List<Comment> getAllComments() {
	        return commentService.getAllComments();
	    }
	
	@GetMapping("account/{userId}/comments/{commentId}")
	public Comment show(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId,
			@PathVariable int commentId, Principal principal) {
		return commentService.show(principal.getName(), commentId);
	}
	
	@PostMapping("account/comment")
	public Comment createComment(
			HttpServletRequest req, 
			HttpServletResponse res, 
			Principal principal,
			@RequestBody Comment comment) {
		
		try {
			return commentService.create(principal.getName(), comment);
	
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setStatus(400);
			comment = null;
		}
		
		return comment;
		
	}

	
	@PutMapping("account/comment/{id}")
	public Comment update(
			HttpServletRequest req, 
			HttpServletResponse res, 
			Principal principal,
			@PathVariable int id, 
			@RequestBody Comment comment) {
		return commentService.update(principal.getName(), id, comment);
	}
	
	@DeleteMapping("account/comment/{tid}")
	public void destroy(
			HttpServletRequest req, 
			HttpServletResponse res, 
			Principal principal,
			@PathVariable int tid) {
		commentService.delete(principal.getName(), tid);
	}

}
