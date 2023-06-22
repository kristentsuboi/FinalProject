package com.skilldistillery.pawrentsplace.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Month;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PetCommentTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private PetComment petComment;

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
		petComment = em.find(PetComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		petComment = null;
	}

	@Test
	void petComment_basic_mapping_test() {
		assertNotNull(petComment);
		assertEquals("healthy dog", petComment.getBody());
		assertEquals(Month.DECEMBER, petComment.getUpdatedAt().getMonth());
		
	}
	@Test
	void petComment_to_pet_onetomany() {
		assertNotNull(petComment);
		assertEquals("brandon", petComment.getPet().getName());
	}
	
	@Test
	void petComment_replies() {
		PetComment comment2 = em.find(PetComment.class, 2);
		assertNotNull(petComment);
		assertNotNull(comment2);
		assertEquals("healthy dog", comment2.getMainComment().getBody());
		assertTrue(petComment.getReplies().size() == 1);
	}

}
