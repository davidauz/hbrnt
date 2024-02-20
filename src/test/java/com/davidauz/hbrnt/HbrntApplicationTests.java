package com.davidauz.hbrnt;

import com.davidauz.hbrnt.entities.City;
import com.davidauz.hbrnt.entities.ExampleEntity;
import com.davidauz.hbrnt.entities.Mayor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//using Hibernate directly without JPA
//Used if  advanced features and optimizations provided by Hibernate that are not available in JPA are required.class
//Hibernate gives full access to its features, optimizations, and integrations with other Hibernate-specific libraries.
//It provides maximum flexibility and control over your persistence layer.
//Using Hibernate directly may sacrifice some level of portability.
class HbrntApplicationTests {

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
		Transaction transaction = null;
		try {
			// Begin transaction
			transaction = session.beginTransaction();

			// create
			ExampleEntity exampleEntity = new ExampleEntity();
			// this is now TRANSIENT (i.e. not saved yet)

			//save
			session.save(exampleEntity);
			// the object is now  Persistent (Managed by the current running Persistence Context) i.e. it has been saved to database

			// Commit transaction
			transaction.commit();

			// Assert that the entity has been assigned an ID
			assertNotNull(exampleEntity.getId());
		} catch (Exception e) {
			// Rollback transaction if an error occurs
			if (transaction != null) {
				transaction.rollback();
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
		Transaction transaction = null;

		City NewYork=new City(null, null, "New York", 10451, "Excelsior")
		,	Bentonville=new City(null, null, "Bentonville", 0, "")
		;
		Mayor EricAdams=new Mayor(null, "Eric", "Adams", 80, null)
		,	StephanieOrman=new Mayor(null,"Stephanie", "Orman", 45, null)
		;
		NewYork.setCityMayor(EricAdams);
		EricAdams.setAssignedCity(NewYork);

		try{
			session.save(NewYork);
			session.save(EricAdams);
			assertNotNull(NewYork.getId());
			assertNotNull(EricAdams.getId());
			System.out.println(NewYork);
			session.remove(EricAdams);
			System.out.println(NewYork);
//Stephanie is not saved yet
			System.out.println(StephanieOrman);
		}catch(Exception e){
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}finally{
			session.close();
		}
		sessionFactory.close();
	}
}

