package com.davidauz.sprngBootTests;

import com.davidauz.sprngBootTests.entities.Cat;
import com.davidauz.sprngBootTests.entities.Lion;
import com.davidauz.sprngBootTests.entities.Mammal;
import com.davidauz.sprngBootTests.repo.CatRepo;
import com.davidauz.sprngBootTests.repo.LionRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableAutoConfiguration // this tell Spring Boot to guess and configure beans and various Spring
// related settings based on the libraries and dependencies included in the project
@ComponentScan(basePackages = { "com.davidauz.sprngBootTests" })  //yes yes, here is your precious configuration
@EntityScan(basePackages = { "com.davidauz.sprngBootTests" })
@DataJpaTest
public class SpringBootHibernateTest {

	@Autowired private CatRepo catrepo;
	@Autowired private LionRepo lionrepo;
	@PersistenceContext private EntityManager entityManager;

	@Test
	public void testRepo(){
		Cat cat = new Cat();
		cat.setTail_length(5);
		cat.setVariety(Cat.catVariety.ANGORA);
		assert(null==cat.getId());// at this point our cat does not have an ID (yet)
		catrepo.save(cat);
//This is actually three inserts:
//Hibernate: insert into mammal (environ,id) values (?,?)
//Hibernate: insert into feline (is_cuddly,id) values (?,?)
//Hibernate: insert into cat (tail_length,variety,id) values (?,?,?)
		assert(null!=cat.getId());// ID is now populated

		cat = new Cat();
		cat.setTail_length(6);
		cat.setVariety(Cat.catVariety.SIAMESE);
		catrepo.save(cat);

		List<Mammal> mammals=new ArrayList<>();
		List<Cat> cats= catrepo.findAll();
		mammals.addAll(cats);

		Lion lion = new Lion();
		lion.setPride_size(10);
		lionrepo.save(lion);

		lion = new Lion();
		lion.setPride_size(12);
		lionrepo.save(lion);

		List<Lion> lions= lionrepo.findAll();
		mammals.addAll(lions);

		mammals.stream().forEach(e->{System.out.println(e);});

		String strTables[]=
				{	"MAMMAL"
						,	"FELINE"
						,	"CAT"
				};

		Arrays.stream(strTables).forEach((str)->{
			String sqlQuery = "SHOW COLUMNS FROM "+str;
			Query query = entityManager.createNativeQuery(sqlQuery);
			List<Object[]> columns = query.getResultList();
			columns.stream()
					.forEach(c->System.out.println("Table "+str+", Column "+c[0]+": "+c[1]))
			;
		});
//Table MAMMAL, Column ENVIRON: TINYINT
//Table MAMMAL, Column ID: BIGINT
//
//Table FELINE, Column IS_CUDDLY: BOOLEAN
//Table FELINE, Column ID: BIGINT
//
//Table CAT, Column TAIL_LENGTH: INTEGER
//Table CAT, Column VARIETY: TINYINT
//Table CAT, Column ID: BIGINT
	}
}
