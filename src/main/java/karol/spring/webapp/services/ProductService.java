package karol.spring.webapp.services;

import karol.spring.webapp.commands.ProductCommand;
import karol.spring.webapp.models.Product;

import java.util.*;

public interface ProductService {

    List<Product> getProducts();

    ProductCommand save(ProductCommand command);

    Product findProductById(Long id);
}
