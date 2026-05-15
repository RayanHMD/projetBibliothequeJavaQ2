package mvc.dataAccess;

import mvc.exception.DataAccessException;
import mvc.model.ResultSearchBookByCategory;

import java.util.ArrayList;

public interface SearchBookByCategoryDataAccess {
    ArrayList<ResultSearchBookByCategory> getBooksByCategory(String category) throws DataAccessException;
}
