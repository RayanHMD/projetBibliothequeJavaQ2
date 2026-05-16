package mvc.dataAccess.recherches;

import dao.SingletonConnection;
import mvc.exception.DataAccessException;
import mvc.model.recherches.ResultSearchBookByCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchBookByCategoryDBAccess implements SearchBookByCategoryDataAccess {

    public ArrayList<ResultSearchBookByCategory> getBooksByCategory(String category) throws DataAccessException {
        ArrayList<ResultSearchBookByCategory> books = new ArrayList<>();

        String sql = "select b.isbn, b.title, a.lastName, a.firstName, pu.name, c.label " +
        "from Category c " +
        "join Book b on b.category = c.label " +
        "join Production p on p.book = b.isbn " +
        "join Author a on a.idAuthor = p.author " +
        "left join Publisher pu on pu.idPublisher = b.publisher " +
        "where c.label = ?;";

        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                ResultSearchBookByCategory book = new ResultSearchBookByCategory(
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
