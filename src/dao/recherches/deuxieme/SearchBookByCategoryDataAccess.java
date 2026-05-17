package dao.recherches.deuxieme;

import exception.DataAccessException;
import mvc.model.recherches.ResultSearchBookByCategory;

import java.util.ArrayList;

public interface SearchBookByCategoryDataAccess {
    ArrayList<ResultSearchBookByCategory> getBooksByCategory(String category) throws DataAccessException;
}
