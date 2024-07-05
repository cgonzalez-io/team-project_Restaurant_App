package restaurant.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import restaurant.database.DatabaseUtil;
import restaurant.entity.Customer;

class CustomerRepositoryImplTest {

  private CustomerRepositoryImpl customerRepository;

  @BeforeEach
  void setUp() throws SQLException {
    customerRepository = new CustomerRepositoryImpl();
    cleanUpDatabase();
    setupSampleData();
  }

  private void cleanUpDatabase() throws SQLException {
    try (Connection conn = DatabaseUtil.getConnection();
         Statement stmt = conn.createStatement()) {
      stmt.executeUpdate("DELETE FROM CUSTOMER");
    }
  }

  private void setupSampleData() throws SQLException {
    customerRepository.insert(new Customer(
        "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St"));
    customerRepository.insert(new Customer(
        "Jane", "Doe", "0987654321", "jane.doe@example.com", "456 Elm St"));
  }

  @Test
  void testInsertValidCustomer() throws SQLException {
    Customer customer = new Customer("John", "Doe", "1234567890",
                                     "john.doe@example.com", "123 Main St");
    customerRepository.insert(customer);
    assertTrue(customer.getCustomerID() > 0);
  }

  @Test
  void testInsertCustomerWithNullValues() throws SQLException {
    Customer customer =
        new Customer("Jane", "Doe", null, "jane.doe@example.com", null);
    customerRepository.insert(customer);
    assertTrue(customer.getCustomerID() > 0);
  }

  @Test
  void testUpdateExistingCustomerWithValidData() throws SQLException {
    // Insert a new customer
    Customer customer = new Customer("Alice", "Smith", "1112223333",
                                     "alice.smith@example.com", "789 Pine St");
    customerRepository.insert(customer);

    // Update the customer
    customer.setFirstName("Alice Updated");
    customer.setLastName("Smith Updated");
    customer.setPhone("3332221111");
    customer.setEmail("alice.updated@example.com");
    customer.setAddress("789 Pine St Updated");
    customerRepository.update(customer);

    // Retrieve the updated customer
    Customer updatedCustomer =
        customerRepository.findById(customer.getCustomerID());
    assertNotNull(updatedCustomer);
    assertEquals("Alice Updated", updatedCustomer.getFirstName());
    assertEquals("Smith Updated", updatedCustomer.getLastName());
    assertEquals("3332221111", updatedCustomer.getPhone());
    assertEquals("alice.updated@example.com", updatedCustomer.getEmail());
    assertEquals("789 Pine St Updated", updatedCustomer.getAddress());
  }

  @Test
  void testUpdateNonExistentCustomer() throws SQLException {
    Customer customer = new Customer(999, "Jane", "Doe", "0987654321",
                                     "jane.doe@example.com", "456 Elm St");
    SQLException thrown = assertThrows(
        SQLException.class, () -> { customerRepository.update(customer); });
    assertEquals("Customer with ID 999 does not exist.", thrown.getMessage());
  }

  @Test
  void testRetrieveExistingCustomerById() throws SQLException {
    Customer expectedCustomer = new Customer(
        "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St");
    customerRepository.insert(expectedCustomer);

    Customer actualCustomer =
        customerRepository.findById(expectedCustomer.getCustomerID());

    assertNotNull(actualCustomer);
    assertEquals(expectedCustomer.getCustomerID(),
                 actualCustomer.getCustomerID());
    assertEquals(expectedCustomer.getFirstName(),
                 actualCustomer.getFirstName());
    assertEquals(expectedCustomer.getLastName(), actualCustomer.getLastName());
    assertEquals(expectedCustomer.getPhone(), actualCustomer.getPhone());
    assertEquals(expectedCustomer.getEmail(), actualCustomer.getEmail());
    assertEquals(expectedCustomer.getAddress(), actualCustomer.getAddress());
  }

  @Test
  void testHandleNonExistentCustomerIdGracefully() throws SQLException {
    Customer actualCustomer = customerRepository.findById(999);
    assertNull(actualCustomer);
  }

  @Test
  void testDeleteExistingCustomer() throws SQLException {
    Customer customer = new Customer("John", "Doe", "1234567890",
                                     "john.doe@example.com", "123 Main St");
    customerRepository.insert(customer);
    customerRepository.delete(customer.getCustomerID());
    assertNull(customerRepository.findById(customer.getCustomerID()));
  }

  @Test
  void testDeleteNonExistentCustomer() throws SQLException {
    int nonExistentCustomerId = 9999;
    customerRepository.delete(nonExistentCustomerId);
    assertNull(customerRepository.findById(nonExistentCustomerId));
  }

  @Test
  void testReturnsAllCustomersWhenDatabaseHasMultipleEntries()
      throws SQLException {
    List<Customer> expectedCustomers = new ArrayList<>();
    expectedCustomers.add(new Customer(1, "John", "Doe", "1234567890",
                                       "john@example.com", "123 Elm St"));
    expectedCustomers.add(new Customer(2, "Jane", "Doe", "0987654321",
                                       "jane@example.com", "456 Oak St"));

    try (MockedStatic<DatabaseUtil> mockedDatabaseUtil =
             Mockito.mockStatic(DatabaseUtil.class)) {
      Connection mockConnection = mock(Connection.class);
      PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
      ResultSet mockResultSet = mock(ResultSet.class);

      when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
      when(mockConnection.prepareStatement(anyString()))
          .thenReturn(mockPreparedStatement);
      when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

      // Mock the ResultSet behavior to simulate multiple entries
      when(mockResultSet.next()).thenReturn(true, true, false);
      when(mockResultSet.getInt("Customer_ID")).thenReturn(1, 2);
      when(mockResultSet.getString("First_Name")).thenReturn("John", "Jane");
      when(mockResultSet.getString("Last_Name")).thenReturn("Doe", "Doe");
      when(mockResultSet.getString("Phone_Number"))
          .thenReturn("1234567890", "0987654321");
      when(mockResultSet.getString("Email"))
          .thenReturn("john@example.com", "jane@example.com");
      when(mockResultSet.getString("Address"))
          .thenReturn("123 Elm St", "456 Oak St");

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
  void testHandlesSqlExceptionsGracefully() {
    try (MockedStatic<DatabaseUtil> mockedDatabaseUtil =
             Mockito.mockStatic(DatabaseUtil.class)) {
      mockedDatabaseUtil.when(DatabaseUtil::getConnection)
          .thenThrow(new SQLException("Database connection error"));
      SQLException thrown = assertThrows(
          SQLException.class, () -> { customerRepository.findAll(); });
      assertEquals("Database connection error", thrown.getMessage());
    }
  }
}
