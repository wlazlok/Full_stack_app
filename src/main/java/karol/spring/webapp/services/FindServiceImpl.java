package karol.spring.webapp.services;

import karol.spring.webapp.models.Product;
import karol.spring.webapp.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindServiceImpl implements FindService {

    private final ProductRepository productRepository;

    public FindServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findProductsThatContainsWord(String word) {

        List<Product> products = new ArrayList<>();

        String org = word;

        for(Product prod: productRepository.findAll()){
            word = org;

            if(products.contains(prod))
                continue;
            if(prod.getProductName().contains(word)) {
                products.add(prod);
                continue;
            }
            word = word.toLowerCase();
            System.out.println(word);
            if (prod.getProductName().contains(word)) {
                products.add(prod);
                continue;
            }
            word = word.substring(0, 1).toUpperCase() + word.substring(1);
            if(prod.getProductName().contains(word)){
                products.add(prod);
                continue;
            }
        }

        return products;
    }
}
