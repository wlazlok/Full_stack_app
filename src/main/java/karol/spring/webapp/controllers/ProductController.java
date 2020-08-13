package karol.spring.webapp.controllers;

import karol.spring.webapp.commands.ProductCommand;
import karol.spring.webapp.models.Product;
import karol.spring.webapp.services.CategoryService;
import karol.spring.webapp.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/new")
    public String getCreateNewProductForm(Model model){

        model.addAttribute("product", new ProductCommand());
        model.addAttribute("category", categoryService.getAllCategories());

        return "product/createNewProductForm";
    }

    @PostMapping("/new")
    public String processAddNewProductForm(@ModelAttribute ProductCommand product, BindingResult result){

//        System.out.println(product.getCategory().getCategoryName());

        if(result.hasErrors()){
            System.out.println(result.getAllErrors());
            return "redirect:/";
        }else{
            ProductCommand savedProduct = productService.save(product);
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
