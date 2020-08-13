package karol.spring.webapp.converters;
import karol.spring.webapp.commands.CategoryCommand;
import karol.spring.webapp.models.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {


    @Override
    public Category convert(CategoryCommand source) {

        if(source == null)
            return null;
        Category category = new Category();
        category.setId(source.getId());
        category.setCategoryName(source.getCategoryName());

        return category;
    }
}
