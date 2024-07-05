package restaurant.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The DatabaseUtil class provides a method to establish a connection to the
 * database.
 */
public class DatabaseUtil {

  public static Connection getConnection() throws SQLException {
    Properties props = new Properties();
    InputStream input = null;
    try {
      input = DatabaseUtil.class.getClassLoader().getResourceAsStream(
          "dbconfig.properties");
      // load properties file
      props.load(input);

      String uri = props.getProperty("db.uri");
      String user = props.getProperty("db.user");
      String password = props.getProperty("db.password");

      return DriverManager.getConnection(uri, user, password);
    } catch (IOException ex) {
      throw new SQLException("Failed to read from properties file.", ex);
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
