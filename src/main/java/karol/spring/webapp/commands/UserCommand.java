package karol.spring.webapp.commands;

import lombok.Data;

@Data
public class UserCommand {

    private Long id;
    private String username;
    private String password;
    private Long roleId;
}
