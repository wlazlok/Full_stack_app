package karol.spring.webapp.commands;

import karol.spring.webapp.models.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CompanyCommand {

    private Long id;
    private String companyName;
    private String companyDescription;
    List<ProductCommand> products = new ArrayList<>();
}
