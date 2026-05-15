package mvc.dataAccess;

import mvc.exception.DataAccessException;
import mvc.model.ResultBookList;

import java.util.ArrayList;

public interface BookListDataAccess {

    ArrayList<ResultBookList> getBookList() throws DataAccessException;
}
