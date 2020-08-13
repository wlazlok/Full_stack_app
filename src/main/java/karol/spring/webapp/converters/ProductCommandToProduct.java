package karol.spring.webapp.converters;

import karol.spring.webapp.commands.ProductCommand;
import karol.spring.webapp.models.Category;
import karol.spring.webapp.models.Product;
import karol.spring.webapp.repositories.CategoryRepository;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Component
public class ProductCommandToProduct implements Converter<ProductCommand, Product> {

    private final CategoryCommandToCategory categoryCommandToCategory;
    private final CategoryRepository categoryRepository;

    public ProductCommandToProduct(CategoryCommandToCategory categoryCommandToCategory, CategoryRepository categoryRepository) {
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.categoryRepository = categoryRepository;
    }

    @SneakyThrows
    @Override
    public Product convert(ProductCommand source) {

        if(source == null)
            return null;
        Product product = new Product();

        product.setId(source.getId());
        product.setProductName(source.getProductName());
        product.setDescription(source.getDescription());
        product.setPrice(source.getPrice());
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(source.getProducedDate());
        LocalDate ldt = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
        product.setProducedDate(ldt);
        List<Category> categories = new ArrayList<>();
        categories = categoryRepository.findAll();

        for (Category cat: categories){
            if(cat.getId() == source.getCategoryId())
                product.setCategory(cat);
        }

        return product;
    }
}