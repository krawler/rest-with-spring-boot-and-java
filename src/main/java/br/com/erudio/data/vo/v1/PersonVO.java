package br.com.erudio.data.vo.v1;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"id","first_name","last_name", "gender"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Mapping("id")
	private Long key;
	//@JsonProperty("id")
	//private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String gender;
			
}
