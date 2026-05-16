package mvc.dataAccess.category;

import mvc.exception.DataAccessException;
import mvc.model.Category;

import java.util.ArrayList;

public interface CategoryDataAccess {

    ArrayList<Category> getAllCategories() throws DataAccessException;
}
