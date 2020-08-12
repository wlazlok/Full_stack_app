package karol.spring.webapp.boostrap;

import karol.spring.webapp.models.Category;
import karol.spring.webapp.models.Company;
import karol.spring.webapp.models.Product;
import karol.spring.webapp.repositories.CategoryRepository;
import karol.spring.webapp.repositories.CompanyRepository;
import karol.spring.webapp.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;

@Component
public class LoadFakeData implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;

    public LoadFakeData(CategoryRepository categoryRepository, ProductRepository productRepository, CompanyRepository companyRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadFakeData();

    }

    private void loadFakeData() {
        // creating test category
        Category testCategory = new Category();
        testCategory.setCategoryName("Test Category");

        // creating test company
        Company testCompany = new Company();
        testCompany.setCompanyName("Test Company");
        testCompany.setCompanyDescription("Short Company test description");

        // creating test product
        Product testProduct = new Product();
        testProduct.setProductName("Test Product");
        testProduct.setPrice(15.54);
        testProduct.setDescription("Long description for testing product");
        testProduct.setShortDescription("Short description");
        testProduct.setProducedDate(LocalDate.now());
        testProduct.setCategory(testCategory);
        testProduct.setCompany(testCompany);

        // adding product to Category list
        testCategory.getProducts().add(testProduct);

        // adding product to Company list
        testCompany.getProducts().add(testProduct);

        categoryRepository.save(testCategory);
        companyRepository.save(testCompany);
        productRepository.save(testProduct);

        System.out.println("### Data loaded ###");
        System.out.println("products: " + productRepository.findAll().size());
        System.out.println("categories: " + categoryRepository.findAll().size());
        System.out.println("companies: " + companyRepository.findAll().size());

    }
}
