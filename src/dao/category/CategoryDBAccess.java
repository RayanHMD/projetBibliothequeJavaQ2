package dao.category;

import dao.SingletonConnection;
import exception.DataAccessException;
import mvc.model.Category;
import java.sql.*;

import java.util.ArrayList;

public class CategoryDBAccess implements CategoryDataAccess {

    @Override
    public ArrayList<Category> getAllCategories() throws DataAccessException {
        ArrayList<Category> categories = new ArrayList<>();

        String sql = "SELECT * FROM Category";

        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getString("label"),
                        resultSet.getDouble("rentalPrice")
                );

                categories.add(category);
            }

        } catch (SQLException exception) {
            throw new DataAccessException(exception.getMessage(), exception);
        }

        return categories;
    }
}
