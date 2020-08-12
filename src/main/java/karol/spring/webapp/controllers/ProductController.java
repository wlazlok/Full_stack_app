package karol.spring.webapp.controllers;

import karol.spring.webapp.models.Product;
import karol.spring.webapp.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Lob;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/new")
    public String getCreateNewProductForm(Model model){

        model.addAttribute("product", new Product());

        return "product/createNewProductForm";
    }

    @PostMapping("/new")
    public String processAddNewProductForm(@ModelAttribute Product product, BindingResult result){

        if(result.hasErrors()){
            System.out.println("ERROR_1");
            return "redirect:/";
        }else{
            Product savedProduct = productService.save(product);
            System.out.println("SUCCESS!");
            return "redirect:/product/details/" + savedProduct.getId();
        }
    }

    @GetMapping("/details/{id}")
    public String showProductDetail(@PathVariable Long id, Model model){

        model.addAttribute("product", productService.findProductById(Long.valueOf(id)));

        return "product/showProductDetails";
    }
}
