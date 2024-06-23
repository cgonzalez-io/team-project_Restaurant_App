package common.utilities.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DatabaseUtil class provides a method to establish a connection to the database.
 */
public class DatabaseUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/RestaurantDB";
    private static final String USER = "nilu";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
