package dao.recherches.premiere;

import java.util.ArrayList;

import exception.DataAccessException;
import mvc.model.recherches.ResultLoanSearchByBookInReader;

public interface LoanSearchByBookInReaderDataAccess {
    ArrayList<ResultLoanSearchByBookInReader> getLoansByReader(Integer readerNumber) throws DataAccessException;
}
