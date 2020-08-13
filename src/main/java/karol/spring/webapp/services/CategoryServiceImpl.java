package karol.spring.webapp.services;

import karol.spring.webapp.commands.CategoryCommand;
import karol.spring.webapp.converters.CategoryToCategoryCommand;
import karol.spring.webapp.models.Category;
import karol.spring.webapp.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryToCategoryCommand categoryToCategoryCommand;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryToCategoryCommand categoryToCategoryCommand) {
        this.categoryRepository = categoryRepository;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Override
    public List<CategoryCommand> getAllCategories() {

        List<CategoryCommand> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(cat -> {
            categories.add(categoryToCategoryCommand.convert(cat));
        });

        return categories;
    }
}
