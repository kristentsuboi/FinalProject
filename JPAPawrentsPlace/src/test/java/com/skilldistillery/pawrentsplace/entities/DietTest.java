package com.skilldistillery.pawrentsplace.entities;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DietTest {
	private static EntityManagerFactory emf;	
	private EntityManager em;
	private Diet diet;
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
			diet = em.find(Diet.class, 1);
			
					}

		@AfterEach
		void tearDown() throws Exception {
			em.close();
			diet = null;
		}

		@Test
		void diet_basic_mapping_test() {
			assertNotNull(diet);
			assertEquals("lose weight", diet.getType());
			assertEquals("3 times a day", diet.getAmount());
			
		}
		@Test
		void diet_to_pet_ManyToOne() {
			assertNotNull(diet);
			assertEquals("brown", diet.getPet().getColor());
		}

	}
