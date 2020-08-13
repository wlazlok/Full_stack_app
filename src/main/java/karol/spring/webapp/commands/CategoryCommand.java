package karol.spring.webapp.commands;

import karol.spring.webapp.models.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryCommand {

    private Long id;
    private String categoryName;
    private List<ProductCommand> products = new ArrayList<>();
}
