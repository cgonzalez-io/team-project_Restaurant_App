package restaurant.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

  // TODO: Move the following constants to a configuration file or environment
  // files.
  private static final String URL = "jdbc:mysql://localhost:3306/RESTAURANT_DB";
  private static final String USER = "root";
  private static final String PASSWORD = "password";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}
