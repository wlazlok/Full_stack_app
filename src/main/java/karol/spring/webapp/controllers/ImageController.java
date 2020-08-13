package karol.spring.webapp.controllers;

import karol.spring.webapp.models.Product;
import karol.spring.webapp.services.ProductService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private final ProductService productService;

    public ImageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/details/image/{id}")
    public void getMainImage(@PathVariable Long id, HttpServletResponse response) throws IOException {

        if (productService.findProductById(id).getMainImage() == null)
            System.out.println("Image not Found!");
        else {
            Product product = productService.findProductById(id);

            byte[] byteArrys = new byte[product.getMainImage().length];

            int i = 0;
            for (Byte b : product.getMainImage()) {
                byteArrys[i++] = b;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArrys);
            IOUtils.copy(is, response.getOutputStream());
        }
    }

    @GetMapping("/product/details/image1/{id}")
    public void getFirstImage(@PathVariable Long id, HttpServletResponse response) throws IOException {

        if (productService.findProductById(id).getMainImage() == null)
            System.out.println("Image not Found!");
        else {
            Product product = productService.findProductById(id);

            byte[] byteArrys = new byte[product.getImage1().length];

            int i = 0;
            for (Byte b : product.getImage1()) {
                byteArrys[i++] = b;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArrys);
            IOUtils.copy(is, response.getOutputStream());
        }
    }

    @GetMapping("/product/details/image2/{id}")
    public void getsecondImage(@PathVariable Long id, HttpServletResponse response) throws IOException {

        if (productService.findProductById(id).getMainImage() == null)
            System.out.println("Image not Found!");
        else {
            Product product = productService.findProductById(id);

            byte[] byteArrys = new byte[product.getImage2().length];

            int i = 0;
            for (Byte b : product.getImage2()) {
                byteArrys[i++] = b;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArrys);
            IOUtils.copy(is, response.getOutputStream());
        }
    }

    @GetMapping("/frontImage/{id}")
    public void getFrontImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
        System.out.println("ZDJ: " + id);
        if (productService.findProductById(id).getMainImage() == null)
            System.out.println("Image not Found!");
        else {
            Product product = productService.findProductById(id);

            byte[] byteArrys = new byte[product.getMainImage().length];

            int i = 0;
            for (Byte b : product.getMainImage()) {
                byteArrys[i++] = b;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArrys);
            IOUtils.copy(is, response.getOutputStream());
        }
    }

//    @GetMapping("/indexImages")
//    public void getIndexImages( HttpServletResponse response) throws IOException {
//        List<Product> products = productService.getProducts();
//
//        System.out.println(products.size());
//
//        byte[] byteArrays = new byte[products.get(1).getMainImage().length];
//        int i = 0;
//        for (Byte b : products.get(1).getMainImage()) {
//            byteArrays[i++] = b;
//        }
//
//
//        response.setContentType("image/jpeg");
//        InputStream is = new ByteArrayInputStream(byteArrays);
//        IOUtils.copy(is, response.getOutputStream());
//
//    }

}
