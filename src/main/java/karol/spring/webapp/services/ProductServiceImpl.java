package karol.spring.webapp.services;

import karol.spring.webapp.models.Product;
import karol.spring.webapp.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {

        List<Product> products = new ArrayList<>();

        productRepository.findAll().forEach(products::add);

        return products;
    }
}
