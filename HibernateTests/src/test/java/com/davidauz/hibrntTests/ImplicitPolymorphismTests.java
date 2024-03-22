package com.davidauz.hibrntTests;

import com.davidauz.hibrntTests.entities.ImplicitPolymorphism.FurnitureChair;
import com.davidauz.hibrntTests.entities.ImplicitPolymorphism.FurnitureSofa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//implicit polymorphism: one table for each concrete class
//as specified in the annotation @MappedSuperclass in the root class FurniturePiece
import java.util.List;

public class ImplicitPolymorphismTests {
	private static SessionFactory sessionFactory;

	@BeforeAll
	static void setup() {
		Configuration configuration = new Configuration().configure();
		sessionFactory = configuration.buildSessionFactory();
	}

	@AfterAll
	static void cleanup() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}


	@Test
	public void testSofa(){
		Session session = sessionFactory.openSession();

		org.hibernate.Transaction tr = null;
		try {
			tr = session.beginTransaction();
			FurnitureSofa sofa=new FurnitureSofa();
//FurnitureSofa <=  FurnitureCanSitOn <= FurniturePiece
			sofa.setComfy_grade(5);
			sofa.setAccomodating_people(4);
			session.save(sofa);
			tr.commit(); // flush to database


			String sqlQuery = "SHOW COLUMNS FROM FURNITURESOFA";
			NativeQuery query = session.createNativeQuery(sqlQuery);
			List<Object[]> columns = query.list();

			columns.stream()
			.forEach(c->System.out.println("Column "+c[0]+": "+c[1]))
			;
//Hibernate creates only one table that includes all the fields in the derived classes:
//Column ID: BIGINT from FurniturePiece
//Column LEGS: INTEGER from FurniturePiece
//Column NAME: CHARACTER VARYING(255) from FurniturePiece
//Column ACCOMODATING_PEOPLE: INTEGER  from FurnitureCanSitOn
//Column COMFY_GRADE: INTEGER from FurnitureCanSitOn
//Column MATERIAL: TINYINT (from FurnitureSofa)
		}catch(Exception e){
			if (tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
	}



	@Test
	public void testChair(){
		Session session = sessionFactory.openSession();

		org.hibernate.Transaction tr = null;
		try {
			tr = session.beginTransaction();
			FurnitureChair chair=new FurnitureChair();
//FurnitureChair <=  FurnitureCanSitOn <= FurniturePiece
			session.save(chair);
			tr.commit();

			String sqlQuery = "SHOW COLUMNS FROM FURNITURECHAIR";
			NativeQuery query = session.createNativeQuery(sqlQuery);
			List<Object[]> columns = query.list();

			columns.stream()
			.forEach(c->System.out.println("Column "+c[0]+": "+c[1]))
			;
//the result is:
//Column ID: BIGINT from FurniturePiece
//Column LEGS: INTEGER from FurniturePiece
//Column NAME: CHARACTER VARYING(255) from FurniturePiece
//Column ACCOMODATING_PEOPLE: INTEGER from FurnitureCanSitOn
//Column COMFY_GRADE: INTEGER from FurnitureCanSitOn
//Column MATERIAL: TINYINT from FurnitureChair
		}catch(Exception e){
			if (tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

}
