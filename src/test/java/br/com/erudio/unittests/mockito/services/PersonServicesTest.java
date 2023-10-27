package br.com.erudio.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import br.com.erudio.services.PersonService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {
	
	MockPerson input;
	
	@InjectMocks
	private PersonService service;
	
	@Mock
	private PersonRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		
		List<Person> people = input.mockEntityList();
		
		when(repository.findAll()).thenReturn(people);
		
		var result = service.findAll();
		assertNotNull(result);
		assertEquals(14, result.size());
		
//		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
//		assertEquals("Address Test1", result.getAddress());
//		assertEquals("First Name Test1", result.getFirstName());
//		assertEquals("Last Name Test1", result.getLastName());
//		assertEquals("Female", result.getGender());
	}

	@Test
	void testFindById() {
		Person person = input.mockEntity(1);
		person.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(person));
		
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getFirstName());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Address test 1", result.getAddress());
		assertEquals("First Name test 1", result.getFirstName());
		assertEquals("Last Name test 1", result.getLastName());
		assertEquals("Female", result.getGender());
}

	@Test
	void testCreate() {
		Person entity = input.mockEntity(1);
		Person persisted = entity;
		persisted.setId(1L);
		
		PersonVO personVO = input.mockVO(1);
		personVO.setKey(1L);
		
		//when(repository.findById(1L)).thenReturn(Optional.of(entity));
		//when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.create(personVO);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("Address test 1", result.getAddress());
		assertEquals("First Name test 1", result.getFirstName());
		assertEquals("Last Name test 1", result.getLastName());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
