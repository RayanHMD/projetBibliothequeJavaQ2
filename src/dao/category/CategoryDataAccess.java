package dao.category;

import exception.DataAccessException;
import mvc.model.Category;

import java.util.ArrayList;

public interface CategoryDataAccess {

    ArrayList<Category> getAllCategories() throws DataAccessException;
}
