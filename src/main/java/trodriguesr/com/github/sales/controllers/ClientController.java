package trodriguesr.com.github.sales.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import trodriguesr.com.github.sales.models.entities.Client;
import trodriguesr.com.github.sales.services.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

	@Autowired
	private ClientService service;

	@GetMapping
	public List<Client> listing() {
		return service.findAll();
	}

	@GetMapping("{id}")
	public Client viewId(@PathVariable Long id) {

		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Client save(@RequestBody @Valid Client client) {
		return service.save(client);

	}

	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {

		service.findById(id).map(client -> {
			service.deleteById(id);
			return client;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));

	}

	@PutMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody @Valid Client client) {

		service.findById(id).map(foundedClient -> {
			client.setId(foundedClient.getId());
			service.save(client);
			return ResponseEntity.noContent().build();
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));

	}

	@GetMapping("find")
	public List<Client> find(Client filterClient) {

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		Example<Client> example = Example.of(filterClient, matcher);

		return service.findAll(example);

	}

}
