package br.com.nuvemapp.integration.repositories;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import br.com.nuvemapp.domain.bar.Bar;
import br.com.nuvemapp.domain.foo.Foo;
import br.com.nuvemapp.integration.config.AppConfig;
import br.com.nuvemapp.integration.repositories.bar.BarRepository;
import br.com.nuvemapp.integration.repositories.bar.BarRepositoryTest;
import br.com.nuvemapp.integration.repositories.foo.FooRepository;
import br.com.nuvemapp.integration.repositories.foo.FooRepositoryTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class BarFooTest {

	private static Logger logFoo = LoggerFactory.getLogger(FooRepositoryTest.class);

	private static Logger logBar = LoggerFactory.getLogger(BarRepositoryTest.class);

	@Autowired
	FooRepository fooRepo;

	@Autowired
	BarRepository barRepo;

	@Test
	public void test() {
		try {
			// salvar
//			Foo foo = new Foo("foo0");
//			fooRepo.saveAndFlush(foo);
//
//			Bar bar = new Bar("bar0");
//			barRepo.saveAndFlush(bar);
						
			List<Bar> listBar = barRepo.findAll();
			
			List<Foo> listFoo = fooRepo.findAll();
			
			for(Bar bar : listBar){
				System.out.println("Id:" + bar.getId() + " Nome: " + bar.getName());
			}
			
			for(Foo foo : listFoo){
				System.out.println("Id:" + foo.getId() + " Nome: " + foo.getName());
			}
			
			

		} catch (Exception e) {
			logFoo.error("Error saving Foo.", e);

			logBar.error("Error saving Bar.", e);
			throw e;
		}
	}

}