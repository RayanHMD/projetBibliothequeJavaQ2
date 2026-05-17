package mvc.controller;

import business.SearchBookByCategoryManager;
import exception.DataAccessException;
import mvc.model.recherches.ResultSearchBookByCategory;

import java.util.ArrayList;

public class SearchBookByCategoryController {
    private SearchBookByCategoryManager searchBookByCategoryManager;

    public SearchBookByCategoryController() {
        searchBookByCategoryManager = new SearchBookByCategoryManager();
    }

    public ArrayList<ResultSearchBookByCategory> getBooksByCategory(String category) throws DataAccessException {
        return searchBookByCategoryManager.getBooksByCategory(category);
    }
}
