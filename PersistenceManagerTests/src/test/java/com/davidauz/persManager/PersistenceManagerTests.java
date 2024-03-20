package com.davidauz.persManager;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//Using PersistenceManager (JDO): PersistenceManager is not part of JPA (Java Persistence API).
//It is instead a core interface provided by the Java Data Objects (JDO) specification, which
//is an alternative to JPA for object-relational mapping (ORM) in Java applications.
//It is used if JDO is preferred or mandated.
//JDO provides features such as transparent persistence, support for object graphs, and a flexible query language.
//It might be suitable for complex object-oriented models.
//JDO has a smaller ecosystem compared to JPA: fewer third-party libraries and community resources available.
//It's also less commonly used in the Java ecosystem compared to JPA and Hibernate.
//needs the file persistence.xml
//IMPORTANT!    run 'mvn datanucleus:enhance' before executing the tests
public class PersistenceManagerTests {

	private static final PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("myPersistenceUnit");
	private static final PersistenceManager pm = pmf.getPersistenceManager();

	@BeforeAll
		public static void setup() {
		try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb")) {
			// Create database and tables if they don't exist
			try (Statement stmt = conn.createStatement()) {
//have to create database first, because cannot use createDatabaseIfNotExist
				stmt.executeUpdate("CREATE SCHEMA IF NOT EXISTS testdb");
				stmt.executeUpdate("USE testdb");
//				stmt.executeUpdate(""" NOT NECESSARY because autoCreateTables is true in persistence.xml
//CREATE TABLE IF NOT EXISTS EXAMPLEPOJO
//(	id INT PRIMARY KEY
//,	int_field INT
//,	string_field VARCHAR(255)
//,	long_field INT
//)
//"""
//);
			}
		} catch ( SQLException e) {
			e.printStackTrace();
		}
	}

	@AfterAll
	public static void cleanup(){
		pm.close();
	}

	@Test
	public void saveTest(){
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
		}
	}
}
