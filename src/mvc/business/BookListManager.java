package mvc.business;

import mvc.dataAccess.BookListDBAccess;
import mvc.dataAccess.BookListDataAccess;
import mvc.exception.DataAccessException;
import mvc.model.ResultBookList;

import java.util.ArrayList;

public class BookListManager {
    private BookListDataAccess bookListDataAccess;

    public BookListManager() {
        this.bookListDataAccess = new BookListDBAccess();
    }

    public ArrayList<ResultBookList> getBookList() throws DataAccessException {
        return bookListDataAccess.getBookList();
    }
}
