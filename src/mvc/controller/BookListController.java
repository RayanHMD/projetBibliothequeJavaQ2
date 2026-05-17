package mvc.controller;

import business.BookListManager;
import exception.DataAccessException;
import mvc.model.recherches.ResultBookList;

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
