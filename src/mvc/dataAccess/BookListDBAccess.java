package mvc.dataAccess;

import dao.SingletonConnection;
import mvc.exception.DataAccessException;
import mvc.model.ResultBookList;
import java.util.ArrayList;
import java.sql.*;

public class BookListDBAccess implements BookListDataAccess {

    @Override
    public ArrayList<ResultBookList> getBookList() throws DataAccessException {
        ArrayList<ResultBookList> books = new ArrayList<>();

        String sql = "SELECT b.isbn, b.title, a.lastName, a.firstName, pu.name, c.label\n" +
                        "FROM Category c\n" +
                        "JOIN Book b ON b.category = c.label\n" +
                        "JOIN Production p ON p.book = b.isbn\n" +
                        "JOIN Author a ON a.idAuthor = p.author\n" +
                        "LEFT JOIN Publisher pu ON pu.idPublisher = b.publisher;";

        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ResultBookList book = new ResultBookList(
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getString("lastName"),
                        resultSet.getString("firstName"),
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
