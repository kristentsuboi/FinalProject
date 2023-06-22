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

class ServiceTypeTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private ServiceType serviceType;

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
		serviceType = em.find(ServiceType.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		serviceType = null;
	}

	@Test
	void test() {
		assertNotNull(serviceType);
		assertEquals("vet clinic", serviceType.getName());
		assertEquals("we take care of your dog", serviceType.getDescription());
		assertNull(serviceType.getImageUrl());
	}
	
	@Test
	void test_Service_has_Businesses() {
		assertNotNull(serviceType);
		assertNotNull(serviceType.getBusinesses());
		assertTrue(serviceType.getBusinesses().size() > 0);
//		assertEquals("", serviceType.getBusinesses().get(0).getName());
		
	}

}
