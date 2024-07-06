package restaurant.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import restaurant.database.DatabaseUtil;
import restaurant.entity.Customer;
import restaurant.entity.Reservation;

public class ReservationRepositoryImplTest {

  private ReservationRepositoryImpl reservationRepository;
  private CustomerRepositoryImpl customerRepository;

  @BeforeEach
  public void setUp() throws SQLException {
    reservationRepository = new ReservationRepositoryImpl();
    customerRepository = new CustomerRepositoryImpl();
    cleanUpDatabase();
    setupSampleData();
  }

  @AfterEach
  public void tearDown() throws SQLException {
    cleanUpDatabase();
  }

  private void cleanUpDatabase() throws SQLException {
    try (Connection conn = DatabaseUtil.getConnection();
         Statement stmt = conn.createStatement()) {
      stmt.executeUpdate("DELETE FROM RESERVATION");
      stmt.executeUpdate("DELETE FROM CUSTOMER");
    }
  }

  private void setupSampleData() throws SQLException {
    // Insert sample customers and retrieve their IDs
    Customer john = new Customer("John", "Doe", "1234567890",
                                 "john.doe@example.com", "123 Main St");
    Customer jane = new Customer("Jane", "Doe", "0987654321",
                                 "jane.doe@example.com", "456 Elm St");
    Customer alice = new Customer("Alice", "Smith", "1112223333",
                                  "alice.smith@example.com", "789 Pine St");

    customerRepository.insert(john);
    customerRepository.insert(jane);
    customerRepository.insert(alice);

    // Insert sample reservations using retrieved customer IDs
    reservationRepository.insert(new Reservation(0, john.getCustomerID(),
                                                 Date.valueOf("2024-06-15"),
                                                 Time.valueOf("18:00:00"), 4));
    reservationRepository.insert(new Reservation(0, jane.getCustomerID(),
                                                 Date.valueOf("2024-06-16"),
                                                 Time.valueOf("19:00:00"), 2));
    reservationRepository.insert(new Reservation(0, alice.getCustomerID(),
                                                 Date.valueOf("2024-06-17"),
                                                 Time.valueOf("20:00:00"), 6));
  }

  @Test
  public void unit_test_insert_valid_reservation() throws SQLException {
    // Ensure the customer exists before inserting a reservation
    Customer customer = new Customer("Test", "User", "0000000000",
                                     "test.user@example.com", "000 Test St");
    customerRepository.insert(customer);

    Reservation reservation =
        new Reservation(0, customer.getCustomerID(), Date.valueOf("2023-01-01"),
                        Time.valueOf("18:00:00"), 4);

    try (MockedStatic<DatabaseUtil> mockedDatabaseUtil =
             Mockito.mockStatic(DatabaseUtil.class)) {
      Connection mockConnection = mock(Connection.class);
      PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
      ResultSet mockGeneratedKeys = mock(ResultSet.class);

      when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
      when(mockConnection.prepareStatement(anyString(),
                                           eq(Statement.RETURN_GENERATED_KEYS)))
          .thenReturn(mockPreparedStatement);
      when(mockPreparedStatement.getGeneratedKeys())
          .thenReturn(mockGeneratedKeys);
      when(mockGeneratedKeys.next()).thenReturn(true);
      when(mockGeneratedKeys.getInt(1)).thenReturn(1);

      reservationRepository.insert(reservation);

      verify(mockPreparedStatement).executeUpdate();
      assertEquals(1, reservation.getReservationID());
    }
  }

  /*@Test
  public void unit_test_update_valid_reservation() throws SQLException {
    // Ensure the customer exists before updating a reservation
    Customer customer = new Customer("Test", "User", "0000000000",
                                     "test.user@example.com", "000 Test St");
    customerRepository.insert(customer);

    Reservation reservation =
        new Reservation(1, customer.getCustomerID(), Date.valueOf("2023-01-01"),
                        Time.valueOf("18:00:00"), 4);

    try (MockedStatic<DatabaseUtil> mockedDatabaseUtil =
             Mockito.mockStatic(DatabaseUtil.class)) {
      Connection mockConnection = mock(Connection.class);
      PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

      when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
      when(mockConnection.prepareStatement(anyString()))
          .thenReturn(mockPreparedStatement);

      reservationRepository.update(reservation);

      verify(mockPreparedStatement).executeUpdate();
    }
  }*/

  @Test
  public void unit_test_find_by_id_existing_reservation() throws SQLException {
    // Ensure the customer exists before finding a reservation
    Customer customer = new Customer("Test", "User", "0000000000",
                                     "test.user@example.com", "000 Test St");
    customerRepository.insert(customer);

    Reservation expectedReservation =
        new Reservation(1, customer.getCustomerID(), Date.valueOf("2023-01-01"),
                        Time.valueOf("18:00:00"), 4);

    try (MockedStatic<DatabaseUtil> mockedDatabaseUtil =
             Mockito.mockStatic(DatabaseUtil.class)) {
      Connection mockConnection = mock(Connection.class);
      PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
      ResultSet mockResultSet = mock(ResultSet.class);

      when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
      when(mockConnection.prepareStatement(anyString()))
          .thenReturn(mockPreparedStatement);
      when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
      when(mockResultSet.next()).thenReturn(true);
      when(mockResultSet.getInt("ReservationID"))
          .thenReturn(expectedReservation.getReservationID());
      when(mockResultSet.getInt("Customer_ID"))
          .thenReturn(expectedReservation.getCustomerID());
      when(mockResultSet.getDate("Date"))
          .thenReturn(expectedReservation.getDate());
      when(mockResultSet.getTime("Time"))
          .thenReturn(expectedReservation.getTime());
      when(mockResultSet.getInt("NumberOfPeople"))
          .thenReturn(expectedReservation.getNumberOfPeople());

      Reservation actualReservation = reservationRepository.findById(1);

      assertEquals(expectedReservation, actualReservation);
    }
  }

  @Test
  public void unit_test_find_by_id_non_existent_reservation()
      throws SQLException {
    try (MockedStatic<DatabaseUtil> mockedDatabaseUtil =
             Mockito.mockStatic(DatabaseUtil.class)) {
      Connection mockConnection = mock(Connection.class);
      PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
      ResultSet mockResultSet = mock(ResultSet.class);

      when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
      when(mockConnection.prepareStatement(anyString()))
          .thenReturn(mockPreparedStatement);
      when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
      when(mockResultSet.next()).thenReturn(false);

      Reservation actualReservation = reservationRepository.findById(999);

      assertNull(actualReservation);
    }
  }

  @Test
  public void unit_test_delete_existing_reservation() throws SQLException {
    try (MockedStatic<DatabaseUtil> mockedDatabaseUtil =
             Mockito.mockStatic(DatabaseUtil.class)) {
      Connection mockConnection = mock(Connection.class);
      PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

      when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
      when(mockConnection.prepareStatement(anyString()))
          .thenReturn(mockPreparedStatement);

      reservationRepository.delete(1);

      verify(mockPreparedStatement).executeUpdate();
    }
  }

  @Test
  public void unit_test_find_all_reservations() throws SQLException {
    List<Reservation> expectedReservations =
        List.of(new Reservation(1, 1, Date.valueOf("2023-01-01"),
                                Time.valueOf("18:00:00"), 4),
                new Reservation(2, 2, Date.valueOf("2023-01-02"),
                                Time.valueOf("19:00:00"), 2));

    try (MockedStatic<DatabaseUtil> mockedDatabaseUtil =
             Mockito.mockStatic(DatabaseUtil.class)) {
      Connection mockConnection = mock(Connection.class);
      Statement mockStatement = mock(Statement.class);
      ResultSet mockResultSet = mock(ResultSet.class);

      when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
      when(mockConnection.createStatement()).thenReturn(mockStatement);
      when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

      when(mockResultSet.next()).thenReturn(true, true, false);
      when(mockResultSet.getInt("ReservationID")).thenReturn(1, 2);
      when(mockResultSet.getInt("Customer_ID")).thenReturn(1, 2);
      when(mockResultSet.getDate("Date"))
          .thenReturn(Date.valueOf("2023-01-01"), Date.valueOf("2023-01-02"));
      when(mockResultSet.getTime("Time"))
          .thenReturn(Time.valueOf("18:00:00"), Time.valueOf("19:00:00"));
      when(mockResultSet.getInt("NumberOfPeople")).thenReturn(4, 2);

      List<Reservation> actualReservations = reservationRepository.findAll();

      assertEquals(expectedReservations, actualReservations);
    }
  }

  // Functional tests
  @Test
  public void func_test_insert_and_find_reservation() throws SQLException {
    // Ensure the customer exists before inserting a reservation
    Customer customer = new Customer("Test", "User", "0000000000",
                                     "test.user@example.com", "000 Test St");
    customerRepository.insert(customer);

    // Arrange
    Reservation reservation =
        new Reservation(0, customer.getCustomerID(), Date.valueOf("2024-06-18"),
                        Time.valueOf("18:00:00"), 4);

    reservationRepository.insert(reservation);

    // Act
    Reservation retrievedReservation =
        reservationRepository.findById(reservation.getReservationID());

    // Assert
    assertNotNull(retrievedReservation, "Expected reservation to be retrieved");
    assertEquals(reservation.getCustomerID(),
                 retrievedReservation.getCustomerID());
    assertEquals(reservation.getDate(), retrievedReservation.getDate());
    assertEquals(reservation.getTime(), retrievedReservation.getTime());
    assertEquals(reservation.getNumberOfPeople(),
                 retrievedReservation.getNumberOfPeople());
  }

  @Test
  public void func_test_insert_and_delete_reservation() throws SQLException {
    // Ensure the customer exists before inserting a reservation
    Customer customer = new Customer("Test", "User", "0000000000",
                                     "test.user@example.com", "000 Test St");
    customerRepository.insert(customer);

    // Arrange
    Reservation reservation =
        new Reservation(0, customer.getCustomerID(), Date.valueOf("2024-06-18"),
                        Time.valueOf("18:00:00"), 4);

    reservationRepository.insert(reservation);

    // Act
    reservationRepository.delete(reservation.getReservationID());
    Reservation deletedReservation =
        reservationRepository.findById(reservation.getReservationID());

    // Assert
    assertNull(deletedReservation, "Expected reservation to be deleted");
  }

  @Test
  public void func_test_find_all_reservations() throws SQLException {
    // Ensure the customer exists before inserting reservations
    Customer john = new Customer("John", "Doe", "1234567890",
                                 "john.doe@example.com", "123 Main St");
    Customer jane = new Customer("Jane", "Doe", "0987654321",
                                 "jane.doe@example.com", "456 Elm St");
    Customer alice = new Customer("Alice", "Smith", "1112223333",
                                  "alice.smith@example.com", "789 Pine St");

    customerRepository.insert(john);
    customerRepository.insert(jane);
    customerRepository.insert(alice);

    // Arrange
    Reservation reservation1 =
        new Reservation(0, john.getCustomerID(), Date.valueOf("2024-06-18"),
                        Time.valueOf("18:00:00"), 4);
    Reservation reservation2 =
        new Reservation(0, jane.getCustomerID(), Date.valueOf("2024-06-19"),
                        Time.valueOf("19:00:00"), 2);

    reservationRepository.insert(reservation1);
    reservationRepository.insert(reservation2);

    // Act
    List<Reservation> reservations = reservationRepository.findAll();

    // Assert
    assertEquals(5, reservations.size()); // 3 existing + 2 new
  }
}
