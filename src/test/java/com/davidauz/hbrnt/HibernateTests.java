package com.davidauz.hbrnt;

import com.davidauz.hbrnt.entities.City;
import com.davidauz.hbrnt.entities.ExamplePojo;
import com.davidauz.hbrnt.entities.Mayor;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;
import java.util.List;

//using Hibernate directly without JPA (JPA is the specification)
//Hibernate implements JPA but it is possible to use Hibernate directly without using JPA.
//Hibernate existed before the JPA specification was introduced, and it offers its own native API for ORM operations.
//Used if advanced features and optimizations provided by Hibernate that are not available in JPA are required.
//Hibernate gives full access to its features, optimizations, and integrations with other Hibernate-specific libraries.
//It provides maximum flexibility and control over your persistence layer.
//Using Hibernate directly may sacrifice some level of portability.
// N.B. this reads hibernate.cfg.xml
class HibernateTests {

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
	public void testSaveEntity(){
		// Opening a session from the SessionFactory
		Session session = sessionFactory.openSession();

		// Creating a transaction
		Transaction tr = null;
		try {
			// Begin transaction
			tr = session.beginTransaction();

			// create
			ExamplePojo examplePojo = new ExamplePojo();
			// this is now TRANSIENT (i.e. not saved yet)

			//save
			session.save(examplePojo);
			// the object is now  Persistent (Managed by the current running Persistence Context) i.e. it has been saved to database

			// Commit transaction
			tr.commit();

			// Assert that the entity has been assigned an ID
			assertNotNull(examplePojo.getId());

// optional
//			String sqlQuery = "SHOW tables";
//			NativeQuery query = session.createNativeQuery(sqlQuery);
//			List<Object[]> tables = query.list();
// see what tables Hibernate just created
			String sqlQuery = "SHOW COLUMNS FROM EXAMPLEPOJO";
			NativeQuery query = session.createNativeQuery(sqlQuery);
			List<Object[]> columns = query.list();

			columns.stream()
			.forEach(c->System.out.println("Column "+c[0]+": "+c[1]))
			;

		} catch (Exception e) {
			// Rollback transaction if an error occurs
			if (tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
		} finally {
		// Closing the session
			session.close();
		// any still existing objects created and saved are now in DETACHED state.
		}

		// Closing the SessionFactory
		sessionFactory.close();
	}

	@Test
	public void TestOneToOneRelation(){
		Session session = sessionFactory.openSession();
		Transaction tr = null;

		City NewYork=new City(null, null, "New York", 10451, "Excelsior")
		,	Bentonville=new City(null, null, "Bentonville", 0, "")
		;
		Mayor EricAdams=new Mayor(null, "Eric", "Adams", 1960, null)
		,	StephanieOrman=new Mayor(null,"Stephanie", "Orman", 1999, null)
		;
		NewYork.setCityMayor(EricAdams);
		EricAdams.setAssignedCity(NewYork);

		try{
			tr = session.beginTransaction();
			session.save(NewYork);
			assertNotNull(NewYork.getId());
			assertNotNull(EricAdams.getId()); // Eric was saved too due to his relationship with New York

			System.out.println(NewYork);

			session.persist(StephanieOrman);

			tr.commit(); // flush to database

//query all mayors
			Query query = session.createQuery("from Mayor");
			List<Mayor> mayors = query.list();
			mayors
			.stream()
			.forEach(m->System.out.println(m));

// There should be one Eric
			String queryString = "SELECT COUNT(*) FROM Mayor WHERE id="+EricAdams.getId();
			query = session.createQuery(queryString);
			Long count=(Long)query.uniqueResult();
			assert(1==count);

//a total of 2 mayors
			queryString = "SELECT COUNT(*) FROM Mayor";
			query = session.createQuery(queryString);
			count=(Long)query.uniqueResult();
			assert(2==count);

			tr.begin();
			session.remove(NewYork); // New York was deleted from database but the Java objects are still with us
			tr.commit(); // flush to database

			query = session.createQuery(queryString);
			assert(1==(Long)query.uniqueResult()); // Eric was deleted together with his city, only Stephanie left

			System.out.println(NewYork);
			System.out.println(StephanieOrman);
		}catch(Exception e){
			if (tr != null) {
				tr.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		sessionFactory.close();
	}
}

