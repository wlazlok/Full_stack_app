package karol.spring.webapp.services;

import karol.spring.webapp.commands.CategoryCommand;
import karol.spring.webapp.models.Category;
import java.util.*;

public interface CategoryService {

    List<CategoryCommand> getAllCategories();
}
