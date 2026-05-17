package business;

import dao.category.CategoryDBAccess;
import dao.category.CategoryDataAccess;
import exception.DataAccessException;
import mvc.model.Category;

import java.util.ArrayList;

public class CategoryManager {
    private CategoryDataAccess categoryDataAccess;

    public CategoryManager() {
        this.categoryDataAccess = new CategoryDBAccess();
    }

    public ArrayList<Category> getAllCategories() throws DataAccessException {
        return categoryDataAccess.getAllCategories();
    }
}
