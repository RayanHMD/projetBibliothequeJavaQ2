package business;

import dao.recherches.deuxieme.SearchBookByCategoryDBAccess;
import dao.recherches.deuxieme.SearchBookByCategoryDataAccess;
import exception.DataAccessException;
import mvc.model.recherches.ResultSearchBookByCategory;

import java.util.ArrayList;

public class SearchBookByCategoryManager {
    private SearchBookByCategoryDataAccess searchBookByCategoryDataAccess;

    public SearchBookByCategoryManager() {
        this.searchBookByCategoryDataAccess = new SearchBookByCategoryDBAccess();
    }

    public ArrayList<ResultSearchBookByCategory> getBooksByCategory(String category) throws DataAccessException {
        return searchBookByCategoryDataAccess.getBooksByCategory(category);
    }
}

