package karol.spring.webapp.services;

import karol.spring.webapp.models.Role;
import karol.spring.webapp.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {

        List<Role> roles = new ArrayList<>();

        roleRepository.findAll().forEach(roles::add);

        return roles;
    }
}
