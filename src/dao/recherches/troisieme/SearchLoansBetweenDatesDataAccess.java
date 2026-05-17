package dao.recherches.troisieme;

import exception.DataAccessException;
import mvc.model.recherches.ResultSearchLoansBetweenDates;

import java.sql.Date;
import java.util.ArrayList;

public interface SearchLoansBetweenDatesDataAccess {

    ArrayList<ResultSearchLoansBetweenDates> getAllLoansBetweenDates(Date startDate, Date endDate) throws DataAccessException;

}
