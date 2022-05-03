package trodriguesr.com.github.sales.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trodriguesr.com.github.sales.models.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
