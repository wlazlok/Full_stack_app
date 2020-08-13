package karol.spring.webapp.converters;

import org.springframework.core.convert.converter.Converter;
import karol.spring.webapp.commands.CategoryCommand;
import karol.spring.webapp.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {


    @Override
    public CategoryCommand convert(Category source) {
        if(source == null)
            return null;
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(source.getId());
        categoryCommand.setCategoryName(source.getCategoryName());


        return categoryCommand;
    }
}
