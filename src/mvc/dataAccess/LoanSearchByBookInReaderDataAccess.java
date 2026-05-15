package mvc.dataAccess;

import java.util.ArrayList;

import mvc.exception.DataAccessException;
import mvc.model.ResultLoanSearchByBookInReader;

public interface LoanSearchByBookInReaderDataAccess {
    ArrayList<ResultLoanSearchByBookInReader> getLoansByReader(Integer readerNumber) throws DataAccessException;
}
