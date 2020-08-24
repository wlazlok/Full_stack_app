package karol.spring.webapp.services;

import karol.spring.webapp.commands.UserCommand;
import karol.spring.webapp.converters.UserCommandToUser;
import karol.spring.webapp.converters.UserToUserCommand;
import karol.spring.webapp.models.User;
import karol.spring.webapp.repositories.RoleRepository;
import karol.spring.webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserCommandToUser userCommandToUser;
    private final UserToUserCommand userToUserCommand;

    public UserServiceImpl(UserCommandToUser userCommandToUser, UserToUserCommand userToUserCommand) {
        this.userCommandToUser = userCommandToUser;
        this.userToUserCommand = userToUserCommand;
    }


    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.getRoleByName("USER")));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void saveUserAfterChangeUsername(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User saveUserCommand(UserCommand user) {

        User detachedUser = userCommandToUser.convert(user);

        User savedUser = userRepository.save(detachedUser);

        return savedUser;
    }


}
