package restaurant.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import restaurant.database.DatabaseUtil;
import restaurant.entity.Customer;

class CustomerRepositoryImplTest {

    @Test
    public void unit_test_insert_valid_customer() throws SQLException {
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        Customer customer = new Customer(0, "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St");

        customerRepository.insert(customer);

        assertTrue(customer.getCustomerID() > 0);
    }

    @Test
    public void unit_test_insert_customer_with_null_values() throws SQLException {
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        Customer customer = new Customer(0, "Jane", "Doe", null, "jane.doe@example.com", null);

        customerRepository.insert(customer);

        assertTrue(customer.getCustomerID() > 0);
    }

    @Test
    public void unit_test_update_existing_customer_with_valid_data() throws SQLException {
        // Arrange
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        Customer customer = new Customer(1, "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St");

        // Act
        customerRepository.update(customer);

        // Assert
        Customer updatedCustomer = customerRepository.findById(1);
        assertNotNull(updatedCustomer);
        assertEquals("John", updatedCustomer.getFirstName());
        assertEquals("Doe", updatedCustomer.getLastName());
        assertEquals("1234567890", updatedCustomer.getPhone());
        assertEquals("john.doe@example.com", updatedCustomer.getEmail());
        assertEquals("123 Main St", updatedCustomer.getAddress());
    }

    @Test
    public void unit_test_update_non_existent_customer() throws SQLException {
        // Arrange
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        Customer customer = new Customer(999, "Jane", "Doe", "0987654321", "jane.doe@example.com", "456 Elm St");

        // Act & Assert
        SQLException thrown = assertThrows(SQLException.class, () -> {
            customerRepository.update(customer);
        });
        assertEquals("Customer with ID 999 does not exist.", thrown.getMessage());
    }


    @Test
    public void unit_test_retrieve_existing_customer_by_valid_id() throws SQLException {
        // Arrange
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        Customer expectedCustomer = new Customer(0, "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St");
        customerRepository.insert(expectedCustomer);

        // Act
        Customer actualCustomer = customerRepository.findById(expectedCustomer.getCustomerID());

        // Assert
        assertNotNull(actualCustomer);
        assertEquals(expectedCustomer.getCustomerID(), actualCustomer.getCustomerID());
        assertEquals(expectedCustomer.getFirstName(), actualCustomer.getFirstName());
        assertEquals(expectedCustomer.getLastName(), actualCustomer.getLastName());
        assertEquals(expectedCustomer.getPhone(), actualCustomer.getPhone());
        assertEquals(expectedCustomer.getEmail(), actualCustomer.getEmail());
        assertEquals(expectedCustomer.getAddress(), actualCustomer.getAddress());
    }

    @Test
    public void unit_test_handle_non_existent_customer_id_gracefully() throws SQLException {
        // Arrange
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();

        // Act
        Customer actualCustomer = customerRepository.findById(999);

        // Assert
        assertNull(actualCustomer);
    }

    @Test
    public void unit_test_delete_existing_customer() throws SQLException {
        // Arrange
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        Customer customer = new Customer(0, "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St");
        customerRepository.insert(customer);

        // Act
        customerRepository.delete(customer.getCustomerID());

        // Assert
        assertNull(customerRepository.findById(customer.getCustomerID()));
    }

    @Test
    public void unit_test_delete_nonexistent_customer() throws SQLException {
        // Arrange
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        int nonExistentCustomerId = 9999;

        // Act
        customerRepository.delete(nonExistentCustomerId);

        // Assert
        assertNull(customerRepository.findById(nonExistentCustomerId));
    }

    @Test
    public void unit_test_returns_all_customers_when_database_has_multiple_entries() throws SQLException {
        // Arrange
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new Customer(1, "John", "Doe", "1234567890", "john@example.com", "123 Elm St"));
        expectedCustomers.add(new Customer(2, "Jane", "Doe", "0987654321", "jane@example.com", "456 Oak St"));

        // Mocking DatabaseUtil to return a connection with predefined data
        try (MockedStatic<DatabaseUtil> mockedDatabaseUtil = Mockito.mockStatic(DatabaseUtil.class)) {
            Connection mockConnection = mock(Connection.class);
            Statement mockStatement = mock(Statement.class);
            ResultSet mockResultSet = mock(ResultSet.class);

            when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
            when(mockConnection.createStatement()).thenReturn(mockStatement);
            when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

            when(mockResultSet.next()).thenReturn(true, true, false);
            when(mockResultSet.getInt("CustomerID")).thenReturn(1, 2);
            when(mockResultSet.getString("FirstName")).thenReturn("John", "Jane");
            when(mockResultSet.getString("LastName")).thenReturn("Doe", "Doe");
            when(mockResultSet.getString("PhoneNumber")).thenReturn("1234567890", "0987654321");
            when(mockResultSet.getString("Email")).thenReturn("john@example.com", "jane@example.com");
            when(mockResultSet.getString("Address")).thenReturn("123 Elm St", "456 Oak St");

            // Act
            List<Customer> actualCustomers = customerRepository.findAll();

            // Assert
            assertEquals(expectedCustomers.size(), actualCustomers.size());
            for (int i = 0; i < expectedCustomers.size(); i++) {
                Customer expected = expectedCustomers.get(i);
                Customer actual = actualCustomers.get(i);
                assertEquals(expected.getCustomerID(), actual.getCustomerID());
                assertEquals(expected.getFirstName(), actual.getFirstName());
                assertEquals(expected.getLastName(), actual.getLastName());
                assertEquals(expected.getPhone(), actual.getPhone());
                assertEquals(expected.getEmail(), actual.getEmail());
                assertEquals(expected.getAddress(), actual.getAddress());
            }
        }
    }

    @Test
    public void unit_test_handles_sql_exceptions_gracefully() {
        // Arrange
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();

        // Mocking DatabaseUtil to throw an SQLException
        try (MockedStatic<DatabaseUtil> mockedDatabaseUtil = Mockito.mockStatic(DatabaseUtil.class)) {
            mockedDatabaseUtil.when(DatabaseUtil::getConnection).thenThrow(new SQLException("Database connection error"));

            // Act & Assert
            SQLException thrown = assertThrows(SQLException.class, () -> {
                customerRepository.findAll();
            });

            assertEquals("Database connection error", thrown.getMessage());
        }
    }
}
