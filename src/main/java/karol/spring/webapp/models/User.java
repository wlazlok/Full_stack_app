package karol.spring.webapp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Transient
    protected String passwordConfirm;

    @ManyToMany
    private Set<Role> roles;
}

