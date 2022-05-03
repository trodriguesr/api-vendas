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

import trodriguesr.com.github.sales.models.entities.Product;
import trodriguesr.com.github.sales.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping
	public List<Product> listing() {

		return service.findAll();
	}

	@GetMapping("{id}")
	public Product viewId(@PathVariable Long id) {

		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Product save(@RequestBody @Valid Product product) {
		return service.save(product);

	}

	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {

		service.findById(id).map(product -> {
			service.deleteById(id);
			return product;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

	}

	@PutMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody @Valid Product product) {

		service.findById(id).map(foundedProduct -> {
			product.setId(foundedProduct.getId());
			service.save(product);
			return ResponseEntity.noContent().build();
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));

	}
	
	@GetMapping("find")
	public List<Product> find(Product filterProduct) {

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		Example<Product> example = Example.of(filterProduct, matcher);

		return service.findAll(example);

	}

}
