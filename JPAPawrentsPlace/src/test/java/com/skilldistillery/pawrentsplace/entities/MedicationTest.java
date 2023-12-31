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

class MedicationTest {

	private static EntityManagerFactory emf;	
	private EntityManager em;
	private Medication medication;
	
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
			em =emf.createEntityManager();
			medication = em.find(Medication.class, 1);
			
					}

		@AfterEach
		void tearDown() throws Exception {
			em.close();
			medication = null;
		}

		@Test
		void medication_test_basic_mapping() {
			assertNotNull(medication);
			assertEquals("tooth paste", medication.getName());
			assertEquals(Month.NOVEMBER, medication.getDateStarted().getMonth());
		}
		
		@Test
		void medication_pet_ManyToOne() {
			assertNotNull(medication);
			assertEquals(Month.DECEMBER, medication.getPet().getBirth().getMonth());
			assertEquals("chihuahua", medication.getPet().getBreed());
		}

	}
