package trodriguesr.com.github.sales.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trodriguesr.com.github.sales.models.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	

}
