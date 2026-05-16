package mvc.controller;

import mvc.business.SearchLoansBetweenDatesManager;
import mvc.exception.DataAccessException;
import mvc.model.recherches.ResultSearchLoansBetweenDates;

import java.sql.Date;
import java.util.ArrayList;

public class SearchLoansBetweenDatesController {
    private SearchLoansBetweenDatesManager searchLoansBetweenDatesManager;

    public SearchLoansBetweenDatesController() {
        searchLoansBetweenDatesManager = new SearchLoansBetweenDatesManager();
    }

    public ArrayList<ResultSearchLoansBetweenDates> getAllLoansBetweenDates(Date startDate, Date endDate) throws DataAccessException {
        return searchLoansBetweenDatesManager.getAllLoansBetweenDates(startDate, endDate);
    }
}
