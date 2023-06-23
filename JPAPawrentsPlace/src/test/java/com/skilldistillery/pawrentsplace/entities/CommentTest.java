package com.skilldistillery.pawrentsplace.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommentTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Comment comment;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAPawrentsPlace");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		comment = em.find(Comment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		comment = null;
	}

	@Test
	void comment_test_basic_mapping() {
		assertNotNull(comment);
		assertEquals("Can i sleep with a dog with rabies?", comment.getBody());
	}
	
	@Test
	void comment_reply_test() {
		assertNotNull(comment);
		assertTrue(comment.getReplies().size() == 1);
		Comment comment2 = em.find(Comment.class, 2);
		assertNotNull(comment2);
		assertEquals("Can i sleep with a dog with rabies?", comment.getMainComment().getBody());
	}

}
