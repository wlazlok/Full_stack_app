package karol.spring.webapp.controllers;

import karol.spring.webapp.repositories.CategoryRepository;
import karol.spring.webapp.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String getAllCategories(Model model){

        model.addAttribute("categories", categoryService.getAllCategories());

        return "category/showAllCategories";
    }

    @GetMapping("/{id}/products")
    public String getAllCategories(@PathVariable Long id, Model model){

        model.addAttribute("products", categoryService.getProductsOfCategory(id));

        return "category/productsOfCategory";
    }
}
