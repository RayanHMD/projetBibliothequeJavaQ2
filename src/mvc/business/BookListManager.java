package mvc.business;

import mvc.dataAccess.bookList.BookListDBAccess;
import mvc.dataAccess.bookList.BookListDataAccess;
import mvc.exception.DataAccessException;
import mvc.model.recherches.ResultBookList;

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
