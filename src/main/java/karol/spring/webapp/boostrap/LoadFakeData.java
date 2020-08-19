package karol.spring.webapp.boostrap;

import karol.spring.webapp.models.*;
import karol.spring.webapp.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class LoadFakeData implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public LoadFakeData(CategoryRepository categoryRepository, ProductRepository productRepository, CompanyRepository companyRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadFakeData();
        loadRoles();
        loadAdmin();
        loadFakeUsers();

    }

    private void loadFakeUsers() {
        User user1 = new User();
        user1.setUsername("karol");
        user1.setPassword(passwordEncoder.encode("karol"));
        user1.setRoles(Arrays.asList(roleRepository.getRoleByName("USER")));

        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("test");
        user2.setPassword(passwordEncoder.encode("test"));
        user2.setRoles(Arrays.asList(roleRepository.getRoleByName("USER")));

        userRepository.save(user2);
    }

    private void loadAdmin() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(Arrays.asList(roleRepository.getRoleByName("ADMIN")));
        userRepository.save(admin);
    }

    private void loadRoles(){
        Role admin = new Role();
        admin.setName("ADMIN");

        Role user = new Role();
        user.setName("USER");

        roleRepository.save(admin);
        roleRepository.save(user);
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

        // adding one more category to test

        Category testCategory2 = new Category();
        testCategory2.setCategoryName("Test 2");
        categoryRepository.save(testCategory2);

        System.out.println("### Data loaded ###");
        System.out.println("products: " + productRepository.findAll().size());
        System.out.println("categories: " + categoryRepository.findAll().size());
        System.out.println("companies: " + companyRepository.findAll().size());

    }
}
