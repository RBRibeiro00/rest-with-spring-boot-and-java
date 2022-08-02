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

import br.com.erudio.model.Person;
import br.com.erudio.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping
	public List<Person> findAllPeople() {
		return personService.findAll();
	}

	@GetMapping(value = "/{id}")
	public Person findById(@PathVariable(value = "id") Long id) throws Exception {
		return personService.findById(id);
	}

	@PostMapping
	public Person create(@RequestBody Person person) throws Exception {
		return personService.create(person);
	}
	
	@PutMapping(value = "/{id}")
	public Person update(@RequestBody Person person) throws Exception {
		return personService.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
		personService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
