package karol.spring.webapp.controllers;

import karol.spring.webapp.services.CategoryService;
import karol.spring.webapp.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public IndexController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String getIndexPage(Model model){

        model.addAttribute("products", productService.getProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "index";
    }

}
