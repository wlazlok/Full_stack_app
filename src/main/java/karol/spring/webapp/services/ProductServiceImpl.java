package karol.spring.webapp.services;

import karol.spring.webapp.commands.ProductCommand;
import karol.spring.webapp.converters.ProductCommandToProduct;
import karol.spring.webapp.converters.ProductToProductCommand;
import karol.spring.webapp.models.Product;
import karol.spring.webapp.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCommandToProduct productCommandToProduct;
    private final ProductToProductCommand productToProductCommand;

    public ProductServiceImpl(ProductRepository productRepository, ProductCommandToProduct productCommandToProduct, ProductToProductCommand productToProductCommand) {
        this.productRepository = productRepository;
        this.productCommandToProduct = productCommandToProduct;
        this.productToProductCommand = productToProductCommand;
    }

    @Override
    public List<Product> getProducts() {

        List<Product> products = new ArrayList<>();

        productRepository.findAll().forEach(products::add);

        return products;
    }

    @Override
    public ProductCommand save(ProductCommand command) {
        Product detachedProduct = productCommandToProduct.convert(command);

        Product savedProduct = productRepository.save(detachedProduct);

        return productToProductCommand.convert(savedProduct);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).get();
    }
}
