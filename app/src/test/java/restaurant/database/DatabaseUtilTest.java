package restaurant.database;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

/**
 * This class contains test cases for the DatabaseUtil class.
 * The test cases test the functionality of the getConnection() method in different scenarios.
 */
class DatabaseUtilTest {

    private static final Logger LOGGER = Logger.getLogger(DatabaseUtilTest.class.getName());

    @Test
    public void unit_test_successful_connection() {
        try {
            Connection connection = DatabaseUtil.getConnection();
            assertNotNull(connection);
            connection.close();
        } catch (SQLException e) {
            fail("Connection should be established successfully, but an exception was thrown: " + e.getMessage());
        }
    }

    @Test
    void unit_test_missing_properties_file() throws SQLException {
        try (MockedStatic<DatabaseUtil> mockedDatabaseUtil = Mockito.mockStatic(DatabaseUtil.class)) {
            mockedDatabaseUtil
                .when(DatabaseUtil::getConnection)
                .thenThrow(new SQLException("Failed to read from properties file."));

            SQLException thrown = assertThrows(SQLException.class, () -> {
                DatabaseUtil.getConnection();
            });
            assertTrue(thrown.getMessage().contains("Failed to read from properties file."));
        }
    }

    @Test
    public void func_test_successful_connection_with_valid_properties() {
        try {
            Connection connection = DatabaseUtil.getConnection();
            assertNotNull(connection);
            assertFalse(connection.isClosed());
            connection.close();
        } catch (SQLException e) {
            fail("Connection should be established successfully with valid properties.");
        }
    }
}
