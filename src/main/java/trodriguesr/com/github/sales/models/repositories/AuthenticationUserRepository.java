package trodriguesr.com.github.sales.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trodriguesr.com.github.sales.models.entities.AuthenticationUser;

@Repository
public interface AuthenticationUserRepository extends JpaRepository<AuthenticationUser, Long> {
	
	Optional<AuthenticationUser> findByLogin(String login);

}
