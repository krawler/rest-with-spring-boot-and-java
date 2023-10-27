package br.com.erudio.unittests.mockito.services;

import java.util.ArrayList;
import java.util.List;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.model.Person;

public class MockPerson {

	public List<Person> mockEntityList(){
		List<Person> people = new ArrayList<Person>();
		for(int i = 0; i < 14; i++) {
			people.add(mockEntity(i));
		}
		return people;
	}

	public List<PersonVO> mockVoList(){
		List<PersonVO> people = new ArrayList<PersonVO>();
		for(int i = 0; i < 14; i++) {
			people.add(mockVO(i));
		}
		return people;
	}
	
	public Person mockEntity(int i) {
		Person person = new Person();
		person.setAddress("Address test " + i);		
		person.setFirstName("First Name test " + i);
		person.setGender(((i % 2) ==0) ? "Male" : "Female");
		person.setLastName("Last Name test " + i);
		return person;
	}

	public PersonVO mockVO(int i) {
		PersonVO personVO = new PersonVO();
		personVO.setAddress("Address test " + i);
		personVO.setFirstName("First Name test " + i);
		personVO.setGender(((i % 2) ==0) ? "Male" : "Female");
		personVO.setLastName("Last Name test " + i);
		return personVO;
	}

}
