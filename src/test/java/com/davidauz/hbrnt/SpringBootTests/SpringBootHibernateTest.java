package com.davidauz.hbrnt.SpringBootTests;

import com.davidauz.hbrnt.entities.JoinedUnions.Cat;
import com.davidauz.hbrnt.repos.CatRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.davidauz.hbrnt" })  //yes yes, here is your precious configuration
@DataJpaTest
public class SpringBootHibernateTest {

	@Autowired private CatRepo catrepo;

	@Test
	public void testRepo(){
		Cat cat = new Cat();
		cat.setTail_length(5);
		cat.setVariety(Cat.catVariety.ANGORA);
		catrepo.save(cat);
	}
}
