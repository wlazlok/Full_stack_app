package karol.spring.webapp.repositories;

import karol.spring.webapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleByName(String roleName);

}
