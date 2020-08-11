package karol.spring.webapp.repositories;

import karol.spring.webapp.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
