package mvc.dataAccess.bookList;

import mvc.exception.DataAccessException;
import mvc.model.recherches.ResultBookList;

import java.util.ArrayList;

public interface BookListDataAccess {

    ArrayList<ResultBookList> getBookList() throws DataAccessException;
}
