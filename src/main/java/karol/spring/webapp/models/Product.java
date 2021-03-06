package karol.spring.webapp.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.*;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;
    private double price;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate producedDate;

    @Lob
    private String description;

    private String shortDescription;

    @Lob
    private Byte[] mainImage;

    @Lob
    private Byte[] image1;

    @Lob
    private Byte[] image2;
}
