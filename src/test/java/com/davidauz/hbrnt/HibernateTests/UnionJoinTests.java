package com.davidauz.hbrnt.HibernateTests;

import com.davidauz.hbrnt.entities.JoinedUnions.Cat;
import com.davidauz.hbrnt.entities.JoinedUnions.Dolphin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

//One table per each entity

public class UnionJoinTests {
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
	public void testInsertMammals(){
		Session session = sessionFactory.openSession();

		// Creating a transaction
		org.hibernate.Transaction tr = null;
		try {
			// Begin transaction
			tr = session.beginTransaction();
			Dolphin dolphin=new Dolphin();
			dolphin.setFish_eaten_in_a_day(10);
			session.save(dolphin);

			Cat cat = new Cat();
			cat.setVariety(Cat.catVariety.MANX);
			cat.setTail_length(2);
			session.save(cat);

			tr.commit(); // flush to database

			String strTables[]=
			{	"MAMMAL"
			,	"FELINE"
			,	"CAT"
			,	"CETACEAN"
			,	"DOLPHIN"
			};

			Arrays.stream(strTables).forEach((str)->{
				String sqlQuery = "SHOW COLUMNS FROM "+str;
				NativeQuery query = session.createNativeQuery(sqlQuery);
				List<Object[]> columns = query.list();
				columns.stream()
				.forEach(c->System.out.println("Table "+str+", Column "+c[0]+": "+c[1]))
				;
			});
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
	public void testSelectMammals(){
		testInsertMammals();
		Session session = sessionFactory.openSession();

		String hql = "FROM Dolphin"; // nb this is the entity name, NOT the table name
		Query query = session.createQuery(hql);
//This is the created query:
// select d1_0.Id
// ,	d1_2.environ
// ,	d1_1.teeth
// ,	d1_0.fish_eaten_in_a_day
// from	Dolphin d1_0
// join	Cetacean d1_1 on d1_0.Id=d1_1.Id
// join	Mammal d1_2 on d1_0.Id=d1_2.Id
		List<Dolphin> dolphins = query.list();
		dolphins.stream()
		.forEach(entity -> {
		System.out.println(entity.toString());
		});

		hql = "FROM Cat";
		query = session.createQuery(hql);
//select c1_0.Id
// ,	c1_2.environ
// ,	c1_1.is_cuddly
// ,	c1_0.tail_length
// ,	c1_0.variety
// from Cat c1_0
// join Feline c1_1 on c1_0.Id=c1_1.Id
// join Mammal c1_2 on c1_0.Id=c1_2.Id
		List<Cat> cats = query.list();
		cats.stream()
		.forEach(entity -> {
		System.out.println(entity.toString());
		});

		session.close();
		}

}
