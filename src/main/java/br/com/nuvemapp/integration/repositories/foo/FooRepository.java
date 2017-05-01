package br.com.nuvemapp.integration.repositories.foo;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nuvemapp.domain.foo.Foo;

public interface FooRepository extends JpaRepository<Foo, Long> {

}
