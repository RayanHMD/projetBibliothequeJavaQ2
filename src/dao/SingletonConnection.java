package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection singleConnection;

    public static Connection getInstance() throws SQLException {
        if(singleConnection == null) {
            singleConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_projet?useSSL=false", "root", "Tigrou007");
        }

        return singleConnection;
    }
}
