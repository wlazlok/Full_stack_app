package karol.spring.webapp.services;

import karol.spring.webapp.models.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findById(Long id);

    void saveUserAfterChangeUsername(User user);
}
