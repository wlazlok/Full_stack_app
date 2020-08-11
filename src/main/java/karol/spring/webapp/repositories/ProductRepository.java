package karol.spring.webapp.repositories;

import karol.spring.webapp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
