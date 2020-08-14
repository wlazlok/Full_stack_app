package karol.spring.webapp.services;

import karol.spring.webapp.commands.CategoryCommand;
import karol.spring.webapp.models.Category;
import karol.spring.webapp.models.Product;

import java.util.*;

public interface CategoryService {

    List<CategoryCommand> getAllCategories();

    List<Product> getProductsOfCategory(Long id);

    Category saveCategory(Category category);

    void deleteCategoryById(Long id);
}
