package karol.spring.webapp.converters;

import karol.spring.webapp.commands.ProductCommand;
import karol.spring.webapp.models.Category;
import karol.spring.webapp.models.Product;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductToProductCommand implements Converter<Product, ProductCommand> {

    private final CategoryToCategoryCommand categoryToCategoryCommand;

    public ProductToProductCommand(CategoryToCategoryCommand categoryToCategoryCommand) {
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Override
    public ProductCommand convert(Product source) {

        if(source == null)
            return null;
        ProductCommand productCommand = new ProductCommand();

        productCommand.setId(source.getId());
        productCommand.setProductName(source.getProductName());
        productCommand.setProducedDate(String.valueOf(source.getProducedDate()));
        productCommand.setDescription(source.getDescription());
        productCommand.setPrice(source.getPrice());

        return productCommand;
    }
}
