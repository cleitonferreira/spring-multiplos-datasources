package br.com.nuvemapp.integration.repositories.foo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import br.com.nuvemapp.domain.foo.Foo;
import br.com.nuvemapp.integration.config.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class FooRepositoryTest {

	private static Logger log = LoggerFactory.getLogger(FooRepositoryTest.class);

	@Autowired
	FooRepository fooRepo;

	@Test
	public void test() {
		try {
			Foo foo = new Foo("foo");
			fooRepo.saveAndFlush(foo);
		} catch (Exception e) {
			log.error("Error saving Foo.", e);
			throw e;
		}
	}

}
