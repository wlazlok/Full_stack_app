package karol.spring.webapp.services;

import karol.spring.webapp.models.Product;

import java.util.*;

public interface FindService {

    List<Product> findProductsThatContainsWord(String word);
}
