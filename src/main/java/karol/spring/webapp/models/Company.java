package karol.spring.webapp.models;

import lombok.Data;
import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    @Lob
    private String companyDescription;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "company")
    List<Product> products = new ArrayList<>();
}
