package br.com.nuvemapp.integration.repositories.bar;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nuvemapp.domain.bar.Bar;

public interface BarRepository extends JpaRepository<Bar, Long> {

}
