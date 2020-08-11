package karol.spring.webapp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
