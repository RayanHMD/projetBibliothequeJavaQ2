package dao.recherches.troisieme;

import dao.SingletonConnection;
import exception.DataAccessException;
import mvc.model.recherches.ResultSearchLoansBetweenDates;

import java.sql.*;
import java.util.ArrayList;


public class SearchLoansBetweenDatesDBAccess implements SearchLoansBetweenDatesDataAccess {

    @Override
    public ArrayList<ResultSearchLoansBetweenDates> getAllLoansBetweenDates(Date startDate, Date endDate) throws DataAccessException {
        ArrayList<ResultSearchLoansBetweenDates> loans = new ArrayList<>();

        String sql = "SELECT l.loanDate, l.maximumLoanDuration, l.actualReturnDate, l.hasExtended, " +
                            "r.lastName, r.firstName, r.email, b.isbn, b.title, ca.label " +
                     "FROM Loan l " +
                     "JOIN Reader r ON r.readerNumber = l.borrower " +
                     "JOIN Copy c ON c.idCopy = l.copy " +
                     "JOIN Book b ON b.isbn = c.book " +
                     "JOIN Category ca ON ca.label = b.category " +
                     "WHERE l.loanDate BETWEEN ? AND ?";

        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, startDate);
            preparedStatement.setDate(2, endDate);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ResultSearchLoansBetweenDates loan = new ResultSearchLoansBetweenDates(
                        resultSet.getDate("loanDate"),
                        resultSet.getInt("maximumLoanDuration"),
                        resultSet.getDate("actualReturnDate"),
                        resultSet.getBoolean("hasExtended"),
                        resultSet.getString("lastName"),
                        resultSet.getString("firstName"),
                        resultSet.getString("email"),
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getString("label")
                );
                loans.add(loan);
            }

        } catch (SQLException exception) {
            throw new DataAccessException(exception.getMessage(), exception);
        }



        return loans;
    }
}
