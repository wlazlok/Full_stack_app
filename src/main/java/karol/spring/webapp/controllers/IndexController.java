package karol.spring.webapp.controllers;

import karol.spring.webapp.models.Product;
import karol.spring.webapp.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final CompanyService companyService;
    private final FindService findService;
    private final SecurityService securityService;

    public IndexController(ProductService productService, CategoryService categoryService, CompanyService companyService, FindService findService, SecurityService securityService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.companyService = companyService;
        this.findService = findService;
        this.securityService = securityService;
    }

    @GetMapping("/")
    public String getIndexPage(Model model){
        model.addAttribute("products", productService.getProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("companies", companyService.getAllCompanies());
        model.addAttribute("username", securityService.getUsernameOfLoggedUser());
        return "index";
    }

    @PostMapping("/")
    public String processFindform(Model model, @RequestParam String word){
        List<Product> odp = findService.findProductsThatContainsWord(word);

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("companies", companyService.getAllCompanies());
        model.addAttribute("products", odp);
        model.addAttribute("username", securityService.getUsernameOfLoggedUser());

        return "product/showProductsForUsers";
    }

}
