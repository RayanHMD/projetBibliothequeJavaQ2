package mvc.dataAccess;

import dao.SingletonConnection;
import mvc.exception.DataAccessException;
import mvc.model.Reader;

import java.sql.*;
import java.util.ArrayList;

public class ReaderDBAccess implements ReaderDataAccess {

    @Override
    public ArrayList<Reader> getAllReaders() throws DataAccessException {
        ArrayList<Reader> readers = new ArrayList<>();

        String sql = "SELECT * FROM Reader";

        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String gender = resultSet.getString("gender");

                Reader reader = new Reader(
                        resultSet.getInt("readerNumber"),
                        resultSet.getString("lastName"),
                        resultSet.getString("firstName"),
                        gender == null ? null : gender.charAt(0),
                        resultSet.getString("streetNumberAndName"),
                        resultSet.getString("numberPhone"),
                        resultSet.getDate("registrationDate"),
                        resultSet.getBoolean("hadPaidRegistration"),
                        resultSet.getDate("birthDate"),
                        resultSet.getString("email"),
                        resultSet.getString("nameLocation"),
                        resultSet.getInt("postalCodeLocation")
                );

                readers.add(reader);
            }

        } catch (SQLException exception) {
            throw new DataAccessException("Impossible de recuperer les lecteurs.", exception);
        }

        return readers;
    }

    @Override
    public void addReader(Reader reader) throws DataAccessException {
        String sql = "INSERT INTO Reader (readerNumber, lastName, firstName, gender, numberPhone, registrationDate, hadPaidRegistration, birthDate, email, streetNumberAndName, nameLocation, postalCodeLocation) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, reader.getReaderNumber());
            statement.setString(2, reader.getLastName());
            statement.setString(3, reader.getFirstName());
            if(reader.getGender() != null) {
                statement.setString(4, reader.getGender().toString());
            }
            else {
                statement.setNull(4, Types.CHAR);
            }
            if(reader.getNumberPhone() != null) {
                statement.setString(5, reader.getNumberPhone());
            }
            else {
                statement.setNull(5, Types.VARCHAR);
            }
            java.sql.Date sqlRegistrationDate = new java.sql.Date(reader.getRegistrationDate().getTime());
            statement.setDate(6, sqlRegistrationDate);
            statement.setBoolean(7, reader.getHadPaidRegistration());
            java.sql.Date sqlBirthDate = new java.sql.Date(reader.getBirthDate().getTime());
            statement.setDate(8, sqlBirthDate);
            statement.setString(9, reader.getEmail());
            statement.setString(10, reader.getStreetNumberAndName());
            statement.setString(11, reader.getLocation().getName());
            statement.setInt(12, reader.getLocation().getPostalCode());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DataAccessException("Impossible d'ajouter le lecteur", exception);
        }
    }

    @Override
    public void updateReader(Reader reader) throws DataAccessException {
        String sql = "UPDATE Reader " +
                     "SET lastName = ?, firstName = ?, gender = ?, numberPhone = ?, registrationDate = ?, " +
                        "hadPaidRegistration = ?, birthDate = ?, email = ?, streetNumberAndName = ?, nameLocation = ?, " +
                        "postalCodeLocation = ?\n" +
                     "WHERE readerNumber = ?";

        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, reader.getLastName());
            statement.setString(2, reader.getFirstName());
            if(reader.getGender() != null) {
                statement.setString(3, reader.getGender().toString());
            }
            else {
                statement.setNull(3, Types.CHAR);
            }
            if(reader.getNumberPhone() != null) {
                statement.setString(4, reader.getNumberPhone());
            }
            else {
                statement.setNull(4, Types.VARCHAR);
            }
            java.sql.Date sqlRegistrationDate = new java.sql.Date(reader.getRegistrationDate().getTime());
            statement.setDate(5, sqlRegistrationDate);
            statement.setBoolean(6, reader.getHadPaidRegistration());
            java.sql.Date sqlBirthDate = new java.sql.Date(reader.getBirthDate().getTime());
            statement.setDate(7, sqlBirthDate);
            statement.setString(8, reader.getEmail());
            statement.setString(9, reader.getStreetNumberAndName());
            statement.setString(10, reader.getLocation().getName());
            statement.setInt(11, reader.getLocation().getPostalCode());
            statement.setInt(12, reader.getReaderNumber());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new DataAccessException("Impossible de modifier le lecteur", exception);
        }

    }
}
