package karol.spring.webapp.controllers;

import karol.spring.webapp.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

    private final ProductService productService;

    public indexController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getIndexPage(Model model){

        model.addAttribute("products", productService.getProducts());

        return "index";
    }
}
