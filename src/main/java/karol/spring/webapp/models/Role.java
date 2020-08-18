package karol.spring.webapp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
}
