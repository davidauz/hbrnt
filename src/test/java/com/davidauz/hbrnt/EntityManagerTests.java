package com.davidauz.hbrnt;

import com.davidauz.hbrnt.entities.City;
import com.davidauz.hbrnt.entities.Mayor;
import jakarta.persistence.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//using EntityManager (JPA)
//used in projects where JPA is the standard persistence API (Java EE or Jakarta EE application)
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
			Mayor mayor = new Mayor(null, "Olivia", "Chow", 1957, null);
			City city = new City(null, null, "Toronto", 416, "Diversity Our Strength");
			em.persist(mayor);
			em.persist(city);
			tr.commit();
		}catch (Exception e) {
			// If an error occurs, rollback the transaction
			if (tr != null && tr.isActive())
				tr.rollback();
			e.printStackTrace();
		}
	}
}
