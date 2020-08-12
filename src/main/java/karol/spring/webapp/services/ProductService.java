package karol.spring.webapp.services;

import karol.spring.webapp.models.Product;

import java.util.*;

public interface ProductService {

    List<Product> getProducts();

    Product save(Product product);

    Product findProductById(Long id);
}
