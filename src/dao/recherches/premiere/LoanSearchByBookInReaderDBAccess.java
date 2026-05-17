package dao.recherches.premiere;

import dao.SingletonConnection;
import exception.DataAccessException;
import mvc.model.recherches.ResultLoanSearchByBookInReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanSearchByBookInReaderDBAccess implements LoanSearchByBookInReaderDataAccess {

    @Override
    public ArrayList<ResultLoanSearchByBookInReader> getLoansByReader(Integer readerNumber) throws DataAccessException {
        ArrayList<ResultLoanSearchByBookInReader> loans = new ArrayList<>();

        String sql = "select b.title, l.loanDate, l.maximumLoanDuration, l.actualReturnDate, r.firstName, r.lastName " +
                        "FROM Reader r " +
                        "JOIN Loan l on r.readerNumber = l.borrower " +
                        "JOIN Copy c on c.idCopy = l.copy " +
                        "JOIN Book b on b.isbn = c.book " +
                        "WHERE r.readerNumber = ?;";

        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, readerNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ResultLoanSearchByBookInReader loan = new ResultLoanSearchByBookInReader(
                        resultSet.getString("title"),
                        resultSet.getDate("loanDate"),
                        resultSet.getInt("maximumLoanDuration"),
                        resultSet.getDate("actualReturnDate"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName")
                );

                loans.add(loan);
            }
        } catch (SQLException exception) {
            throw new DataAccessException(exception.getMessage(), exception);
        }

        return loans;
    }
}
