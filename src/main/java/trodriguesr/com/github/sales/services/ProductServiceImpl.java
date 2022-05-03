package trodriguesr.com.github.sales.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import trodriguesr.com.github.sales.models.entities.Product;
import trodriguesr.com.github.sales.models.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
	
		return repository.findAll();
	}

	@Override
	public Optional<Product> findById(Long id) {
		
		return repository.findById(id);
	}

	@Override
	public Product save(Product product) {
		
		return repository.save(product);
	}

	@Override
	public void deleteById(Long id) {
		
		repository.deleteById(id);
		
	}

	@Override
	public List<Product> findAll(Example<Product> example) {
		return repository.findAll(example);
	}

}
