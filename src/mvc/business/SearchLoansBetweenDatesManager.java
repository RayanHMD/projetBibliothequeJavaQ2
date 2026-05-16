package mvc.business;

import mvc.dataAccess.recherches.troisieme.SearchLoansBetweenDatesDBAccess;
import mvc.dataAccess.recherches.troisieme.SearchLoansBetweenDatesDataAccess;
import mvc.exception.DataAccessException;
import mvc.model.recherches.ResultSearchLoansBetweenDates;

import java.sql.Date;
import java.util.ArrayList;

public class SearchLoansBetweenDatesManager {
    private SearchLoansBetweenDatesDataAccess searchLoansBetweenDatesDataAccess;

    public SearchLoansBetweenDatesManager() {
        this.searchLoansBetweenDatesDataAccess = new SearchLoansBetweenDatesDBAccess();
    }

    public ArrayList<ResultSearchLoansBetweenDates> getAllLoansBetweenDates(Date startDate, Date endDate) throws DataAccessException {
        return searchLoansBetweenDatesDataAccess.getAllLoansBetweenDates(startDate, endDate);
    }
}
