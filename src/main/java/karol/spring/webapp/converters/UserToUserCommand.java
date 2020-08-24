package karol.spring.webapp.converters;

import karol.spring.webapp.commands.UserCommand;
import karol.spring.webapp.models.User;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class UserToUserCommand implements Converter<User, UserCommand>{


    @Override
    public UserCommand convert(User source) {

        UserCommand userCommand = new UserCommand();

        userCommand.setId(source.getId());
        userCommand.setPassword(source.getPassword());
        userCommand.setUsername(source.getUsername());

        return userCommand;
    }
}
