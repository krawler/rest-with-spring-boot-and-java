package br.com.erudio.mapper.custom;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.model.Person;

@Service
public class PersonMapper {
	
	public PersonVOV2 convertEntitytoVo(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setAddress(person.getAddress());
		vo.setBirthDay(LocalDate.now());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setGender(person.getGender());
		return vo;
	}
	
	public Person convertVoToEntity(PersonVOV2 vo) {
		Person person = new Person();
		person.setAddress(vo.getAddress());
		//person.setBirthDay(LocalDate.now());
		person.setFirstName(vo.getFirstName());
		person.setLastName(vo.getLastName());
		person.setGender(vo.getGender());
		return person;
	}

}
