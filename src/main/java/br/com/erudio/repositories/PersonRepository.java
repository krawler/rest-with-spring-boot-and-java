package br.com.erudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.com.erudio.model.Person;

@Component
public interface PersonRepository extends JpaRepository<Person, Long> {

}
