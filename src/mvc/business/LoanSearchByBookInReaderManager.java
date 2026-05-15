package mvc.business;

import mvc.dataAccess.LoanSearchByBookInReaderDBAccess;
import mvc.dataAccess.LoanSearchByBookInReaderDataAccess;
import mvc.exception.DataAccessException;
import mvc.model.ResultLoanSearchByBookInReader;

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
