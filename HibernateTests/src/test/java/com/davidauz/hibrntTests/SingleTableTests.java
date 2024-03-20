package com.davidauz.hibrntTests;

import com.davidauz.hibrntTests.entities.TablePerClass.Ginseng;
import com.davidauz.hibrntTests.entities.TablePerClass.Oak;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

//One table per class *hierarchy*

public class SingleTableTests {
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
	public void testGinseng(){
		Session session = sessionFactory.openSession();

		// Creating a transaction
		org.hibernate.Transaction tr = null;
		try {
			// Begin transaction
			tr = session.beginTransaction();
			Ginseng ginseng=new Ginseng();
			ginseng.setGinseng_origin(Ginseng.GinsengOrigin.KOREAN);
			session.save(ginseng);

			Oak oak = new Oak();
			oak.setN_acorns(1600);
			session.save(oak);

			tr.commit(); // flush to database

			String sqlQuery = "SHOW COLUMNS FROM PLANT";
			NativeQuery query = session.createNativeQuery(sqlQuery);
			List<Object[]> columns = query.list();

			columns.stream()
			.forEach(c->System.out.println("Column "+c[0]+": "+c[1]))
			;
////the result is:
//Column PLANT_TYPE: CHARACTER VARYING(31) from Plant
//Column ID: BIGINT from Plant
//Column BORNYEAR: INTEGER from Plant
//Column HERB_TYPE: TINYINT from Herb
//Column GINSENG_ORIGIN: TINYINT from Ginseng
//Column TRUNK_HEIGHT: FLOAT(53) from Tree
//Column N_ACORNS: INTEGER from Oak
//N.B. Hibernate creates a common table for all entities defined with InheritanceType.SINGLE_TABLE
//but the set of columns wil be the absolutely minimum necessary, i.e. if in the hibernate.cfg.xml
//there are only Ginseng and Oak and nothing else, there won't be any column from the other
//classes in the hierarchy: no n_of_strawberries from Strawberry, no RoseColor from Rose.
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
