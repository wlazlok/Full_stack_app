package karol.spring.webapp.controllers;

import karol.spring.webapp.models.Product;
import karol.spring.webapp.services.CategoryService;
import karol.spring.webapp.services.CompanyService;
import karol.spring.webapp.services.FindService;
import karol.spring.webapp.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class IndexController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final CompanyService companyService;
    private final FindService findService;

    public IndexController(ProductService productService, CategoryService categoryService, CompanyService companyService, FindService findService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.companyService = companyService;
        this.findService = findService;
    }

    @GetMapping("/")
    public String getIndexPage(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("products", productService.getProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("companies", companyService.getAllCompanies());
        return "index";
    }

    @PostMapping("/")
    public String processFindform(Product product, Model model, String word){

        List<Product> odp = findService.findProductsThatContainsWord(product.getProductName());

        model.addAttribute("products", odp);

        return "product/showProductsForUsers";
    }

}
