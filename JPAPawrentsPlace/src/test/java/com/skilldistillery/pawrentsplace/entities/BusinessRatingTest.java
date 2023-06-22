package com.skilldistillery.pawrentsplace.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.crypto.AEADBadTagException;
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
		id.setUserId(3);	
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
		assertEquals("excellent service", bizRating.getRemark());
		assertEquals(2022, bizRating.getCreatedAt().getYear());	
	}
	
	@Test
	void rating_mapping_Many_To_One() {
		assertNotNull(bizRating);
		assertEquals(1, bizRating.getBusiness().getId());
		
	}

}
