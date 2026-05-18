package dao.location;

import dao.SingletonConnection;
import exception.DataAccessException;
import mvc.model.Location;
import mvc.model.LocationFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocationDBAccess implements LocationDataAccess {


    @Override
    public ArrayList<Location> getAllLocations() throws DataAccessException {
        ArrayList<Location> locations = new ArrayList<>();

        String sql = "SELECT name, postalCode FROM Location";

        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Location location = LocationFactory.getLocation(
                        resultSet.getString("name"),
                        resultSet.getInt("postalCode")
                );
                locations.add(location);
            }

        } catch (SQLException exception) {
            throw new DataAccessException("Impossible de récupérer les localites", exception);
        }

        return locations;
    }
}
