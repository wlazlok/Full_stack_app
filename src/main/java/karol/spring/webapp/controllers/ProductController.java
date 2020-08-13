package karol.spring.webapp.controllers;

import karol.spring.webapp.commands.ProductCommand;
import karol.spring.webapp.models.Product;
import karol.spring.webapp.services.CategoryService;
import karol.spring.webapp.services.CompanyService;
import karol.spring.webapp.services.ProductService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final CompanyService companyService;

    public ProductController(ProductService productService, CategoryService categoryService, CompanyService companyService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.companyService = companyService;
    }

    @GetMapping("/new")
    public String getCreateNewProductForm(Model model){

        model.addAttribute("product", new ProductCommand());
        model.addAttribute("category", categoryService.getAllCategories());
        model.addAttribute("companies", companyService.getAllCompanies());

        return "product/createNewProductForm";
    }

    @PostMapping("/new")
    public String processAddNewProductForm(@ModelAttribute ProductCommand product, BindingResult result, @RequestParam("files") MultipartFile[] files){


        if(result.hasErrors()){
            System.out.println(result.getAllErrors());
            return "redirect:/";
        }else{
            convertImages(product, files);

            ProductCommand savedProduct = productService.save(product);
            System.out.println("SUCCESS!");
            return "redirect:/product/details/" + savedProduct.getId();
        }
    }

    private void convertImages(@ModelAttribute ProductCommand product, @RequestParam("files") MultipartFile[] files) {
        try {
            for(int x = 0; x< 3; x++) {

                if(files[x].getBytes().length == 0)
                    continue;

                Byte[] byteObject = new Byte[files[x].getBytes().length];
                int i = 0;

                for(byte b : files[x].getBytes())
                    byteObject[i++] = b;

                if(x == 0)
                    product.setMainImage(byteObject);
                if(x == 1)
                    product.setImage1(byteObject);
                if(x == 2)
                    product.setImage2(byteObject);

                byteObject = null;
                i = 0;
            }
        } catch (IOException e) {
            System.out.println("ERROR_2");
        }
    }

    @GetMapping("/details/{id}")
    public String showProductDetail(@PathVariable Long id, Model model, HttpServletResponse response) throws IOException {

        model.addAttribute("product", productService.findProductById(Long.valueOf(id)));

        return "product/showProductDetails";
    }
}
