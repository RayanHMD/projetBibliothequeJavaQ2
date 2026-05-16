package mvc.business;

import mvc.dataAccess.recherches.premiere.LoanSearchByBookInReaderDBAccess;
import mvc.dataAccess.recherches.premiere.LoanSearchByBookInReaderDataAccess;
import mvc.exception.DataAccessException;
import mvc.model.recherches.ResultLoanSearchByBookInReader;

import java.util.ArrayList;

public class LoanSearchByBookInReaderManager {
    private LoanSearchByBookInReaderDataAccess loanSearchByBookInReaderDataAccess;

    public LoanSearchByBookInReaderManager() {
        this.loanSearchByBookInReaderDataAccess = new LoanSearchByBookInReaderDBAccess();
    }

    public ArrayList<ResultLoanSearchByBookInReader> getLoansByReader(Integer readerNumber) throws DataAccessException {
        return loanSearchByBookInReaderDataAccess.getLoansByReader(readerNumber);
    }
}
