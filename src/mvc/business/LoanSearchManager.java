package mvc.business;

import mvc.dataAccess.LoanSearchDBAccess;
import mvc.dataAccess.LoanSearchDataAccess;
import mvc.exception.DataAccessException;
import mvc.model.ReaderLoanSearchResult;

import java.util.ArrayList;

public class LoanSearchManager {
    private LoanSearchDataAccess loanSearchDataAccess;

    public LoanSearchManager() {
        this.loanSearchDataAccess = new LoanSearchDBAccess();
    }

    public ArrayList<ReaderLoanSearchResult> getLoansByReader(Integer readerNumber) throws DataAccessException {
        return loanSearchDataAccess.getLoansByReader(readerNumber);
    }
}
