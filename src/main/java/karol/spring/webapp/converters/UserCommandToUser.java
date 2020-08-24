package karol.spring.webapp.converters;

import karol.spring.webapp.commands.UserCommand;
import karol.spring.webapp.models.Role;
import karol.spring.webapp.models.User;
import karol.spring.webapp.services.RoleService;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserCommandToUser implements Converter<UserCommand, User> {

    private final RoleService roleService;

    public UserCommandToUser(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public User convert(UserCommand source) {

        User user = new User();
        user.setId(source.getId());
        user.setUsername(source.getUsername());
        Collection<Role> roles = new ArrayList<>();
        roles.add(roleService.getRoleById(source.getRoleId()));
        user.setRoles(roles);
        user.setPassword(source.getPassword());

        return user;
    }
}
