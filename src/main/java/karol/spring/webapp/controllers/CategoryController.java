package karol.spring.webapp.controllers;

import karol.spring.webapp.models.Category;
import karol.spring.webapp.services.CategoryService;
import karol.spring.webapp.services.CompanyService;
import karol.spring.webapp.services.SecurityService;
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
    private final CompanyService companyService;
    private final SecurityService securityService;

    public CategoryController(CategoryService categoryService, CompanyService companyService, SecurityService securityService) {
        this.categoryService = categoryService;
        this.companyService = companyService;
        this.securityService = securityService;
    }

    @GetMapping
    public String getAllCategories(Model model){

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("companies", companyService.getAllCompanies());
        model.addAttribute("username", securityService.getUsernameOfLoggedUser());
        return "category/showAllCategories";
    }

    @GetMapping("/{id}/products")
    public String getAllCategories(@PathVariable Long id, Model model){

        model.addAttribute("products", categoryService.getProductsOfCategory(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("companies", companyService.getAllCompanies());
        model.addAttribute("username", securityService.getUsernameOfLoggedUser());
        return "category/productsOfCategory";
    }

    @GetMapping("/{id}")
    public String getAllProductsOfCategory(@PathVariable Long id, Model model){

        model.addAttribute("products", categoryService.getProductsOfCategory(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("companies", companyService.getAllCompanies());
        model.addAttribute("username", securityService.getUsernameOfLoggedUser());

        return "product/showProductsForUsers";
    }

    @GetMapping("/new")
    public String createNewCategory(Model model){

        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("companies", companyService.getAllCompanies());
        model.addAttribute("username", securityService.getUsernameOfLoggedUser());

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
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("companies", companyService.getAllCompanies());
        model.addAttribute("username", securityService.getUsernameOfLoggedUser());

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
