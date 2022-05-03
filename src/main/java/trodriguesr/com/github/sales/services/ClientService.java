package trodriguesr.com.github.sales.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;

import trodriguesr.com.github.sales.models.entities.Client;

public interface ClientService {
	
	public List<Client> findAll();
	public List<Client> findAll(Example<Client> example);
	public Optional<Client> findById(Long id);
	public Client save(Client client);
	public void deleteById(Long id);

}
