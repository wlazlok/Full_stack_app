package karol.spring.webapp.services;

import karol.spring.webapp.commands.UserCommand;
import karol.spring.webapp.models.User;
import java.util.*;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findById(Long id);

    void saveUserAfterChangeUsername(User user);

    List<User> getAllUsers();

    User saveUserCommand(UserCommand user);
}
