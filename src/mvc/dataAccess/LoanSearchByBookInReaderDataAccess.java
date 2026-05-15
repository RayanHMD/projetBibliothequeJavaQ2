package mvc.dataAccess;

import java.util.ArrayList;

import mvc.exception.DataAccessException;
import mvc.model.LoanSearchByBookInReaderResult;

public interface LoanSearchByBookInReaderDataAccess {
    ArrayList<LoanSearchByBookInReaderResult> getLoansByReader(Integer readerNumber) throws DataAccessException;
}
