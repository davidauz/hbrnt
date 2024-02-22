package com.davidauz.hbrnt;


import com.davidauz.hbrnt.entities.ExamplePojo;
import org.junit.jupiter.api.Test;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

//Using PersistenceManager (JDO):
//Used if JDO is preferred or mandated.
//JDO provides features such as transparent persistence, support for object graphs, and a flexible query language.
//It might be suitable for complex object-oriented models.
//JDO has a smaller ecosystem compared to JPA: fewer third-party libraries and community resources available.
//It's also less commonly used in the Java ecosystem compared to JPA and Hibernate.
public class PersistenceManagerTests {

	private static final PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("myPersistenceUnit");


	@Test
	public void saveTest(){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tr = pm.currentTransaction();

		try {
			tr.begin();

			ExamplePojo pojo1 = new ExamplePojo();
			ExamplePojo pojo2 = new ExamplePojo();

			pm.makePersistent(pojo1);
			pm.makePersistent(pojo2);

			tr.commit();
		} catch (Exception e) {
			if (tr.isActive()) {
				tr.rollback();
			}
			e.printStackTrace();
		} finally {
			pm.close();
		}
	}
}
