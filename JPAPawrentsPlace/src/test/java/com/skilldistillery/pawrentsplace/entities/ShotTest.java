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

class ShotTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Shot shot;

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
		shot = em.find(Shot.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		shot = null;
	}

	@Test
	void shot_test_basic_mapping() {
		assertNotNull(shot);
		assertEquals("rabies", shot.getName());
		assertEquals(Month.OCTOBER, shot.getDateAdministered().getMonth());
	}
	@Test
	void shot_test_ManyToOne_Pet() {
		assertNotNull(shot);
		assertEquals(false, shot.getPet().isMicrochipped());
		assertEquals("n/a", shot.getPet().getAllergies());
	}

}
