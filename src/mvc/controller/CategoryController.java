package mvc.controller;

import business.CategoryManager;
import exception.DataAccessException;
import mvc.model.Category;

import java.util.ArrayList;

public class CategoryController {
    private CategoryManager categoryManager;

    public CategoryController() {
        this.categoryManager = new CategoryManager();
    }

    public ArrayList<Category> getAllCategories() throws DataAccessException {
        return categoryManager.getAllCategories();
    }
}
