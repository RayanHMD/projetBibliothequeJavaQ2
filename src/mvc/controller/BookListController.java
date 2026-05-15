package mvc.controller;

import mvc.business.BookListManager;
import mvc.exception.DataAccessException;
import mvc.model.ResultBookList;

import java.util.ArrayList;

public class BookListController {
    private BookListManager bookListManager;

    public BookListController() {
        this.bookListManager = new BookListManager();
    }

    public ArrayList<ResultBookList> getBookList() throws DataAccessException {
        return bookListManager.getBookList();
    }
}
