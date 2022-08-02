package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.services.PersonService;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping
	public List<PersonVO> findAllPeople() {
		return personService.findAll();
	}

	@GetMapping(value = "/{id}")
	public PersonVO findById(@PathVariable(value = "id") Long id) throws Exception {
		return personService.findById(id);
	}

	@PostMapping
	public PersonVO create(@RequestBody PersonVO person) throws Exception {
		return personService.create(person);
	}

	@PostMapping(value = "/v2")
	public PersonVOV2 createV2(@RequestBody PersonVOV2 person) throws Exception {
		return personService.createV2(person);
	}

	@PutMapping
	public PersonVO update(@RequestBody PersonVO person) throws Exception {
		return personService.update(person);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
		personService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
