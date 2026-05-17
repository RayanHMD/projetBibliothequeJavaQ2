package dao.bookList;

import exception.DataAccessException;
import mvc.model.recherches.ResultBookList;

import java.util.ArrayList;

public interface BookListDataAccess {

    ArrayList<ResultBookList> getBookList() throws DataAccessException;
}
