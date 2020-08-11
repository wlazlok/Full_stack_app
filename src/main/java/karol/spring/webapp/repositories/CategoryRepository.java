package karol.spring.webapp.repositories;

import karol.spring.webapp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
