package karol.spring.webapp.controllers;

import karol.spring.webapp.commands.CategoryCommand;
import karol.spring.webapp.models.Category;
import karol.spring.webapp.repositories.CategoryRepository;
import karol.spring.webapp.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
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

    @GetMapping("/new")
    public String createNewCategory(Model model){

        model.addAttribute("category", new Category());

        return "category/createNewCategoryForm";
    }

    @PostMapping("/new")
    public String processCreateNewCategoryForm(@ModelAttribute Category category, BindingResult result){
        if(result.hasErrors()){
            System.out.println("Problem during creating new Category");
            return "redirect:/category/createNewCategoryForm";
        }else {
            Category savedCategory = categoryService.saveCategory(category);

            return "redirect:/category";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id){

        categoryService.deleteCategoryById(id);

        return "redirect:/category";
    }

    @GetMapping("/{id}/edit")
    public String editCategory(Model model, @PathVariable Long id){

        model.addAttribute("category", categoryService.getCategoryById(id));

        return "category/editCategory";
    }

    @PostMapping("/{id}/edit")
    @Transactional
    public String processEditCategory(@Validated Category category, BindingResult result, @PathVariable Long id){

        if(result.hasErrors()){
            System.out.println("Problem during updating category");
            return "redirect:/category/createNewCategoryForm";
        }else{

            Category savedCategory = categoryService.getCategoryById(id);

            System.out.println(savedCategory.getCategoryName());

            savedCategory.setCategoryName(category.getCategoryName());
            savedCategory.setProducts(category.getProducts());

            categoryService.saveCategory(savedCategory);

            return "redirect:/category";
        }
    }
}
