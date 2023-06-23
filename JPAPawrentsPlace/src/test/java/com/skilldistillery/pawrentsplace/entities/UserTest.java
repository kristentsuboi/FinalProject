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

class UserTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test() {
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
		assertEquals("$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS", user.getPassword());
	}
	
	@Test
	void test_User_has_business() {
		User user2 = em.find(User.class, 2);
		assertNotNull(user2);
		assertNotNull(user2.getBusiness());
		assertEquals("fatastic pets", user2.getBusiness().getName());
	}
	
	@Test
	void test_User_has_address() {
		assertNotNull(user);
		assertNotNull(user.getAddress());
		assertEquals(3, user.getAddress().getId());
	}
	
	@Test
	void test_User_has_comments() {
		User user3 = em.find(User.class, 3);
		assertNotNull(user3);
		assertNotNull(user3.getComments());
		assertEquals(1, user3.getComments().get(0).getId());
	}
	
	@Test
	void test_User_has_BusinessesUsed() {
		User user3 = em.find(User.class, 3);
		assertNotNull(user3.getBusinessesUsed());
		assertTrue(user3.getBusinessesUsed().size() > 0);
		assertEquals(1, user3.getBusinessesUsed().get(0).getId());
	}
	
	@Test
	void test_User_has_MedicalNotes() {
		User user2 = em.find(User.class, 2);
		assertNotNull(user2.getMedicalNotes());
		assertTrue(user2.getMedicalNotes().size() > 0);
		assertEquals(1, user2.getMedicalNotes().get(0).getId());
	}
	
	@Test
	void test_User_has_PetComments() {
		User user2 = em.find(User.class, 2);
		assertNotNull(user2);
		assertNotNull(user2.getPetComments());
		assertTrue(user2.getPetComments().size() > 0);
		assertEquals(1, user2.getPetComments().get(0).getId());
	}
	
	@Test
	void test_User_has_Pets() {
		User user3 = em.find(User.class, 3);
		assertNotNull(user3);
		assertNotNull(user3.getPets());
		assertTrue(user3.getPetComments().size() > 0);
		assertEquals("brandon", user3.getPets().get(0).getName());
	}
	
	@Test
	void test_User_has_PetClients() {
		User user2 = em.find(User.class, 2);
		assertNotNull(user2);
		assertNotNull(user2.getPetClients());
		assertTrue(user2.getPetClients().size() > 0);
		assertEquals(1, user2.getPetClients().get(0).getId());
	}
}
