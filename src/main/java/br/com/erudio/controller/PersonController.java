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
import br.com.erudio.services.PersonService;
import br.com.erudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name="People", description = "Endpointd for managing people")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@RequestMapping(method = RequestMethod.GET,  
					produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Finds all people", description="Finds all people",
						  tags= {"People"},
						  responses = {
								  @ApiResponse(description="Success", responseCode = "200",
										  	   content = {
										  			@Content(
										  				mediaType = "application/json",
										  				array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
										  			)   
										  	   }),
								  @ApiResponse(description="Bad Request", responseCode = "400", content = @Content),
								  @ApiResponse(description="Unauthorized", responseCode = "401", content = @Content),
								  @ApiResponse(description="Not Found", responseCode = "404", content =@Content),
								  @ApiResponse(description="Internal error", responseCode = "500",content =	@Content),
	})	
	public List<PersonVO> findAll(){
		return service.findAll();
	}
	
	
	@RequestMapping(value="/{id}" ,method = RequestMethod.GET,  
					produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Finds one people by id", description="Finds one people by id",
	  tags= {"People"},
	  responses = {
			  @ApiResponse(description="Success", responseCode = "200",
					  	   content = {
					  			@Content(
					  				mediaType = "application/json",
					  				array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
					  			)   
					  	   }),
			  @ApiResponse(description="Bad Request", responseCode = "400", content = @Content),
			  @ApiResponse(description="Unauthorized", responseCode = "401", content = @Content),
			  @ApiResponse(description="Not Found", responseCode = "404", content =@Content),
			  @ApiResponse(description="Internal error", responseCode = "500",content =	@Content),
	})	
	public PersonVO findById(@PathVariable(value = "id") Long id) {
		
		return service.findById(id);
	}	

	@RequestMapping(method = RequestMethod.POST, 
					consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }, 
					produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Creates one person", description="Creates one person",
	  tags= {"People"},
	  responses = {
			  @ApiResponse(description="Success", responseCode = "200",
					  	   content = {
					  			@Content(
					  				mediaType = "application/json",
					  				array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
					  			)   
					  	   }),
			  @ApiResponse(description="Bad Request", responseCode = "400", content = @Content),
			  @ApiResponse(description="Unauthorized", responseCode = "401", content = @Content),
			  @ApiResponse(description="Not Found", responseCode = "404", content =@Content),
			  @ApiResponse(description="Internal error", responseCode = "500",content =	@Content),
	})		
	public PersonVO create(@RequestBody PersonVO personVO) {	
		return  service.create(personVO);
	}
	 
	@RequestMapping(method = RequestMethod.PUT, 
					consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }, 
					produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	public PersonVO update(@RequestBody PersonVO personVO) {
		return service.update(personVO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
