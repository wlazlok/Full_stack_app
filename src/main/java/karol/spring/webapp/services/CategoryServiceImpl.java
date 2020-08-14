package karol.spring.webapp.services;

import karol.spring.webapp.commands.CategoryCommand;
import karol.spring.webapp.converters.CategoryToCategoryCommand;
import karol.spring.webapp.models.Category;
import karol.spring.webapp.models.Product;
import karol.spring.webapp.repositories.CategoryRepository;
import karol.spring.webapp.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryToCategoryCommand categoryToCategoryCommand, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.productRepository = productRepository;
    }

    @Override
    public List<CategoryCommand> getAllCategories() {

        List<CategoryCommand> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(cat -> {
            categories.add(categoryToCategoryCommand.convert(cat));
        });

        return categories;
    }

    @Override
    public List<Product> getProductsOfCategory(Long id) {

        List<Product> products;
        Category category = categoryRepository.findById(id).get();
        products = category.getProducts();

        return products;
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id).get();

        for(Product prod: category.getProducts()){
            prod.setCategory(null);
            productRepository.save(prod);
        }

        categoryRepository.delete(category);
    }
}
