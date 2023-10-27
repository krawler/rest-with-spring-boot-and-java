package br.com.erudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.services.PersonService;
import br.com.erudio.util.MediaType;

@RestController
@RequestMapping("/api/person/v2")
public class PersonV2Controller {
	
	@Autowired
	private PersonService service;
	@Autowired
	private PersonMapper mapper;
	
	@RequestMapping(method = RequestMethod.GET,  
			produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<PersonVO> findAll(){
		return DozerMapper.parseListObjects(service.findAll(), PersonVO.class);
	}
	
	@RequestMapping(value="/{id}" ,method = RequestMethod.GET,  
				produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public PersonVO findById(@PathVariable(value = "id") Long id) {
	
		return DozerMapper.parseObject(service.findById(id), PersonVO.class);
	}	
	
	@RequestMapping(method = RequestMethod.POST, 
				consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, 
				produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public PersonVO create(@RequestBody PersonVO personVO) {	
		return  service.create(personVO);
	}
	
	@RequestMapping(method = RequestMethod.PUT, 
				consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }, 
				produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public PersonVO update(@RequestBody PersonVO personVO) {
		return service.update(personVO);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
