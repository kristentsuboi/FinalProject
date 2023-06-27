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
	@PostMapping("account/{userId}/comments")
	public Comment create(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId,
			@RequestBody Comment comment, Principal principal) {
		try {
			comment = commentService.create(principal.getName(), userId, comment);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(comment.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			comment = null;
		}
		return comment;
	}
	
	@PutMapping("account/{userId}/comments/{commentId}")
	public Comment update(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId, @RequestBody Comment comment,
			@PathVariable int commentId, Principal principal) {
		Comment updated = null;
		try {
			updated = commentService.update(principal.getName(), userId, comment, commentId);
			if (updated == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return updated;
	}
	@DeleteMapping("account/{userId}/comments/{commentId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId,
			@PathVariable int commentId, Principal principal) {
		boolean deleted = commentService.delete(principal.getName(), commentId, userId);
		if (deleted) {
			res.setStatus(204);
		} else {
			res.setStatus(404);
		}
	}
	

}
