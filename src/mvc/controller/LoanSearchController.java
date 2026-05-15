package mvc.controller;

import mvc.business.LoanSearchManager;
import mvc.exception.DataAccessException;
import mvc.model.ReaderLoanSearchResult;

import java.util.ArrayList;

public class LoanSearchController {
    private LoanSearchManager loanSearchManager;

    public LoanSearchController() {
        this.loanSearchManager = new LoanSearchManager();
    }

    public ArrayList<ReaderLoanSearchResult> getLoansByReader(Integer readerNumber) throws DataAccessException {
        return loanSearchManager.getLoansByReader(readerNumber);
    }
}
