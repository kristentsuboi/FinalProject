package com.skilldistillery.pawrentsplace.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PetTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Pet pet;

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
		pet = em.find(Pet.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		pet = null;
	}

	@Test
	void pet_basic_mapping_test() {
		assertNotNull(pet);
		assertEquals("brandon", pet.getName());
	}
	@Test
	void pet_diet_onetomany_mapping() {
		assertNotNull(pet);
		assertTrue(pet.getDiets().size() == 1);
	}
	@Test
	void pet_shot_onetomany_mapping() {
		assertNotNull(pet);
		assertTrue(pet.getShots().size() == 1);
	}
	@Test
	void pet_medication_onetomany_mapping() {
		assertNotNull(pet);
		assertTrue(pet.getMedications().size() == 1);
	}
	@Test
	void pet_petComment_onetomany_mapping() {
		assertNotNull(pet);
		assertTrue(pet.getPetComments().size() == 2);
	}
	@Test
	void pet_medicalNotes_onetomany_mapping() {
		assertNotNull(pet);
		assertTrue(pet.getMedicalNotes().size() == 1);
	}

}
