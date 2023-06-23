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

class MedicalNoteTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private MedicalNote medicalNote;

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
		medicalNote = em.find(MedicalNote.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		medicalNote = null;
	}

	@Test
	void medicalNote_basic_mapping_test() {
		assertNotNull(medicalNote);
		assertEquals("call the other doctor", medicalNote.getNotes());
	}
	@Test
	void medicalNote_to_pet_ManyToOne() {
		assertNotNull(medicalNote);
		assertEquals(1, medicalNote.getPet().getId());
	}
	@Test
	void medicalNote_to_user_ManyToOne() {
		assertNotNull(medicalNote);
		assertEquals("vet", medicalNote.getUser().getUsername());
	}

}
