package dao.recherches.deuxieme;

import dao.SingletonConnection;
import exception.DataAccessException;
import mvc.model.recherches.ResultSearchBookByCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchBookByCategoryDBAccess implements SearchBookByCategoryDataAccess {

    @Override
    public ArrayList<ResultSearchBookByCategory> getBooksByCategory(String category) throws DataAccessException {
        ArrayList<ResultSearchBookByCategory> books = new ArrayList<>();

        String sql = "SELECT b.isbn, b.title, " +
                        "GROUP_CONCAT(CONCAT(a.firstName, ' ', a.lastName) SEPARATOR ', ') AS authors, " +
                        "pu.name, c.label " +
                    "FROM Category c " +
                    "JOIN Book b on b.category = c.label " +
                    "JOIN Production p on p.book = b.isbn " +
                    "JOIN Author a on a.idAuthor = p.author " +
                    "LEFT JOIN Publisher pu on pu.idPublisher = b.publisher " +
                    "WHERE c.label = ? " +
                    "GROUP BY b.isbn, b.title, pu.name, c.label";

        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                ResultSearchBookByCategory book = new ResultSearchBookByCategory(
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
