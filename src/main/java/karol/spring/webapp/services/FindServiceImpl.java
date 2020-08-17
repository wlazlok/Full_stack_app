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
        // todo naprawic blad poniewaz czasami dodaje te same wartosci (napisac lepiej ta funkcje) !!!!!
        for(Product prod: productRepository.findAll()){
            word = org;
            if(prod.getProductName().contains(word)) {
                products.add(prod);
            }
            word.toLowerCase();
            if (prod.getProductName().contains(word)) {
                products.add(prod);
            }
            word = word.substring(0, 1).toUpperCase() + word.substring(1);
            if(prod.getProductName().contains(word)){
                products.add(prod);
            }
        }

        return products;
    }
}
