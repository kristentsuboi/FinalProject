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

class BusinessTest {

	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Business business;

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
		business = em.find(Business.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		business = null;
	}

	@Test
	void test() {
		assertNotNull(business);
		assertEquals("fatastic pets", business.getName());
		assertEquals("1234325555", business.getPhone());
	}
	
	@Test
	void test_One_to_One() {
		assertNotNull(business);
		assertEquals("123 vet st", business.getAddress().getStreet());
		assertEquals("fayetteville", business.getAddress().getCity());
		assertEquals("NC", business.getAddress().getState());
		assertEquals("28311", business.getAddress().getZipCode());

	}

}