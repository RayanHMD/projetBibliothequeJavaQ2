package mvc.business;

import mvc.dataAccess.recherches.deuxieme.SearchBookByCategoryDBAccess;
import mvc.dataAccess.recherches.deuxieme.SearchBookByCategoryDataAccess;
import mvc.exception.DataAccessException;
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

