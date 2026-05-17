package dao.reader;

import dao.SingletonConnection;
import exception.DataAccessException;
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
        String sql = "INSERT INTO Reader (lastName, firstName, gender, numberPhone, registrationDate, hadPaidRegistration, birthDate, email, streetNumberAndName, nameLocation, postalCodeLocation) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, reader.getLastName());
            statement.setString(2, reader.getFirstName());

            if (reader.getGender() != null) {
                statement.setString(3, reader.getGender().toString());
            } else {
                statement.setNull(3, Types.CHAR);
            }

            if (reader.getNumberPhone() != null) {
                statement.setString(4, reader.getNumberPhone());
            } else {
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

    @Override
    public void deleteReader(Reader reader) throws DataAccessException {
        Connection connection = null;

        try {
            connection = SingletonConnection.getInstance();
            connection.setAutoCommit(false);

            if(hasCurrentLoan(connection, reader.getReaderNumber())) {
                throw new SQLException("Impossible de supprimer le lecteur car il possede des emprunts.");
            }
            ArrayList<Integer> reservationsIds = getReservationsIds(connection, reader.getReaderNumber());

            deleteNotifications(connection, reader.getReaderNumber());
            deleteReservations(connection,reservationsIds);
            deleteCard(connection, reader.getReaderNumber());
            deleteOldLoans(connection, reader.getReaderNumber());
            deleteReaderRow(connection, reader.getReaderNumber());

            connection.commit();
            
        } catch (SQLException exception) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException rollbackException) {
                throw new DataAccessException("Erreur pendant l'annulation de la suppression du lecteur.", rollbackException);
            }
            throw new DataAccessException(exception.getMessage(), exception);

        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException exception) {
                throw new DataAccessException("Impossible de retablir l'auto-commit.", exception);
            }
        }
    }

    private boolean hasCurrentLoan(Connection connection, Integer readerNumber) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Loan WHERE borrower = ? AND actualReturnDate IS NULL";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, readerNumber);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next() && resultSet.getInt(1) > 0;
    }

    private ArrayList<Integer> getReservationsIds(Connection connection, Integer readerNumber) throws SQLException {
        String sql = "SELECT reservation FROM Notification WHERE reader = ?";
        ArrayList<Integer> reservationIds = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, readerNumber);
        ResultSet resultSet = statement.executeQuery();

        while(resultSet.next()) {
            reservationIds.add(resultSet.getInt("reservation"));
        }

        return reservationIds;
    }

    private void deleteNotifications(Connection connection, Integer readerNumber) throws SQLException {
        String sql = "DELETE FROM Notification WHERE reader = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, readerNumber);
        statement.executeUpdate();
    }

    private void deleteReservations(Connection connection, ArrayList<Integer> reservationIds) throws SQLException {
        String sql = "DELETE FROM Reservation WHERE idReservation = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        for(Integer reservationId : reservationIds) {
            statement.setInt(1, reservationId);
            statement.executeUpdate();
        }
    }

    private void deleteCard(Connection connection, Integer readerNumber) throws SQLException {
        String sql = "DELETE FROM Card WHERE reader = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, readerNumber);
        statement.executeUpdate();
    }

    private void deleteOldLoans(Connection connection, Integer readerNumber) throws SQLException {
        String sql = "DELETE FROM Loan WHERE borrower = ? AND actualReturnDate IS NOT NULL";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, readerNumber);
        statement.executeUpdate();
    }

    private void deleteReaderRow(Connection connection, Integer readerNumber) throws SQLException {
        String sql = "DELETE FROM Reader WHERE readerNumber = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, readerNumber);
        statement.executeUpdate();
    }
}