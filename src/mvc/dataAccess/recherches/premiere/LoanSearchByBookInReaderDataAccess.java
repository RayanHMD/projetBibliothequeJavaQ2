package mvc.dataAccess.recherches.premiere;

import java.util.ArrayList;

import mvc.exception.DataAccessException;
import mvc.model.recherches.ResultLoanSearchByBookInReader;

public interface LoanSearchByBookInReaderDataAccess {
    ArrayList<ResultLoanSearchByBookInReader> getLoansByReader(Integer readerNumber) throws DataAccessException;
}
