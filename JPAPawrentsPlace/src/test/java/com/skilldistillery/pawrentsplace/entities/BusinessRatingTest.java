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

class BusinessRatingTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private BusinessRating bizRating;

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
		BusinessRatingId id = new BusinessRatingId();
		id.setUserId(1);
		id.setBusinessId(1);
		em = emf.createEntityManager();
		bizRating = em.find(BusinessRating.class, id);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		bizRating = null;
	}
	
	@Test
	void rating_mapping() {
		assertNotNull(bizRating);
		assertNotNull(bizRating.getRating());
//		assertEquals(, bizRating.getRating());
	}
	
//	@Test 
//	void rating_user() {
//		assertNotNull(bizRating);
//		assertNotNull(bizRating.getUser());
//		assertEquals("", bizRating.getUser().getRole());
//	}
//	@Test 
//	void rating_business() {
//		assertNotNull(bizRating);
//		assertNotNull(bizRating.getBusiness());
//		assertEquals("",bizRating.getBusiness().getName());
//	}

}
