package mvc.dataAccess;

import java.util.ArrayList;

import mvc.exception.DataAccessException;
import mvc.model.ReaderLoanSearchResult;

public interface LoanSearchDataAccess {
    ArrayList<ReaderLoanSearchResult> getLoansByReader(Integer readerNumber) throws DataAccessException;
}
