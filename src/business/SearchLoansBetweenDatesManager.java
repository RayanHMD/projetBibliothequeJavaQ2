package business;

import dao.recherches.troisieme.SearchLoansBetweenDatesDBAccess;
import dao.recherches.troisieme.SearchLoansBetweenDatesDataAccess;
import exception.DataAccessException;
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
