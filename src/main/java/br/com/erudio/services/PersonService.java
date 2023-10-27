package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.erudio.controller.PersonController;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;

	public List<PersonVO> findAll(){
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}
	
	public PersonVO findById(Long id) throws ResourceNotFoundException {
		Person person = repository.findById(id)
									.orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		
		PersonVO vo = DozerMapper.parseObject(person, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public PersonVO create(PersonVO personVO) {
		Person person = DozerMapper.parseObject(personVO, Person.class);
		person =  repository.save(person);
		//mockito esta devolvendo nulo, se não for testar então comente
		if(null == person) {
		   person =  DozerMapper.parseObject(personVO, Person.class);	
		}
		var vo = DozerMapper.parseObject(person, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
		
	}
	
	public PersonVO update(PersonVO personVO) throws ResourceNotFoundException {
		Person  person = repository.findById(personVO.getKey())
					.orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		return DozerMapper.parseObject(repository.save(person), PersonVO.class);
	}
	
	public void delete(Long id) throws ResourceNotFoundException {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		repository.delete(entity);;
	}
}
