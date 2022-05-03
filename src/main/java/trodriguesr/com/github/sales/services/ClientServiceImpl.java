package trodriguesr.com.github.sales.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import trodriguesr.com.github.sales.models.entities.Client;
import trodriguesr.com.github.sales.models.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository repository;

	
	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		
		
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Client> findById(Long id) {
		
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Client save(Client client) {
		return repository.save(client);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		
		repository.deleteById(id);
		
	}

	@Override
	public List<Client> findAll(Example<Client> example) {
		
		return repository.findAll(example);
	}



}
