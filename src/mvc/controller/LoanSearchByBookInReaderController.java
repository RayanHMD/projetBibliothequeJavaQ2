package mvc.controller;

import mvc.business.LoanSearchByBookInReaderManager;
import mvc.exception.DataAccessException;
import mvc.model.recherches.ResultLoanSearchByBookInReader;

import java.util.ArrayList;

public class LoanSearchByBookInReaderController {
    private LoanSearchByBookInReaderManager loanSearchByBookInReaderManager;

    public LoanSearchByBookInReaderController() {
        this.loanSearchByBookInReaderManager = new LoanSearchByBookInReaderManager();
    }

    public ArrayList<ResultLoanSearchByBookInReader> getLoansByReader(Integer readerNumber) throws DataAccessException {
        return loanSearchByBookInReaderManager.getLoansByReader(readerNumber);
    }
}
