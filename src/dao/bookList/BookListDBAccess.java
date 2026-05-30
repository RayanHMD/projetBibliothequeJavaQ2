package dao.bookList;

import dao.SingletonConnection;
import exception.DataAccessException;
import mvc.model.recherches.ResultBookList;
import java.util.ArrayList;
import java.sql.*;

public class BookListDBAccess implements BookListDataAccess {

    @Override
    public ArrayList<ResultBookList> getBookList() throws DataAccessException {
        ArrayList<ResultBookList> books = new ArrayList<>();

        String sql = "SELECT b.isbn, b.title, " +
                        "GROUP_CONCAT(CONCAT(a.firstName, ' ', a.lastName) SEPARATOR ', ') AS authors, " +
                        " pu.name, c.label " +
                    "FROM Category c " +
                    "JOIN Book b ON b.category = c.label " +
                    "JOIN Production p ON p.book = b.isbn " +
                    "JOIN Author a ON a.idAuthor = p.author " +
                    "LEFT JOIN Publisher pu ON pu.idPublisher = b.publisher " +
                    "GROUP BY b.isbn, b.title, pu.name, c.label";

        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ResultBookList book = new ResultBookList(
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getString("authors"),
                        resultSet.getString("name"),
                        resultSet.getString("label")
                );

                books.add(book);
            }

        } catch (SQLException exception) {
            throw new DataAccessException(exception.getMessage(), exception);
        }

        return books;
    }
}
