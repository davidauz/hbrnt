package com.davidauz.hbrnt;

import com.davidauz.hbrnt.entities.City;
import com.davidauz.hbrnt.entities.Mayor;
import jakarta.persistence.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//using EntityManager (Hibernate with JPA)
//EntityManager is part of JPA (the specification: Java Persistence API).
//JPA provides a set of interfaces and classes for managing relational data in Java applications.
//EntityManager is one of the core interfaces in JPA, and hibernate is the implementation of that interface.
//Actually it is an interface defined by the JPA specification.
//It is used in projects where JPA is the standard persistence API (Java EE or Jakarta EE application)
//ensures portability across different JPA providers.
//JPA is a standardized API, it doesn't expose all of Hibernate's features.
//So it might be less flexible than using Hibernate directly.
//N.B. this needs META-INF/persistence.xml, I mean it, it needs the file badly
public class EntityManagerTests {
	static EntityManagerFactory emf;
	static EntityManager em;
	static EntityTransaction tr;


	@BeforeAll
	static void setup() {
		 emf = Persistence.createEntityManagerFactory("examplePU");
		 em = emf.createEntityManager();
		 tr = em.getTransaction();
	}

	@AfterAll
	static void cleanup() {
		if (tr != null && tr.isActive())
			tr.rollback();
		em.close();
		emf.close();
	}


		@Test
	public void saveEntity() {
		try {
			tr.begin();
			Mayor OliviaChow = new Mayor(null, "Olivia", "Chow", 1957, null);
			City Toronto = new City(null, null, "Toronto", 416, "Diversity Our Strength");
			em.persist(OliviaChow);
			em.persist(Toronto);
			System.out.println(OliviaChow);
			System.out.println(Toronto);
			Toronto.setCityMayor(OliviaChow);
			System.out.println(Toronto);
			tr.commit();
		}catch (Exception e) {
			// If an error occurs, rollback the transaction
			if (tr != null && tr.isActive())
				tr.rollback();
			e.printStackTrace();
		}
	}
}
