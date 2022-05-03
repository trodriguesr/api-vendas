package trodriguesr.com.github.sales.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;

import trodriguesr.com.github.sales.models.entities.Product;

public interface ProductService {
	
	public List<Product> findAll();
	public List<Product> findAll(Example<Product> example);
	public Optional<Product> findById(Long id);
	public Product save(Product product);
	public void deleteById(Long id);	
	

}
