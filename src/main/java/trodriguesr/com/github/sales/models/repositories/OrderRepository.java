package trodriguesr.com.github.sales.models.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import trodriguesr.com.github.sales.models.entities.Client;
import trodriguesr.com.github.sales.models.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	public List<Order> findByClient(Client client);
	
	@Query(value = "select o from Order o left join fetch o.items where o.id = :id")
	public Optional<Order> findByIdFetchItems(@Param("id") Long id);

}
