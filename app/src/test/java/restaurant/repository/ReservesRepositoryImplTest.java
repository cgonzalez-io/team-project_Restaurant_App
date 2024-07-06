package restaurant.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.database.DatabaseUtil;
import restaurant.entity.ReservationTable;
import restaurant.interfaces.ReservesRepository;

public class ReservesRepositoryImplTest {

  private ReservesRepository repository;

  @BeforeEach
  public void setUp() throws SQLException {
    repository = new ReservesRepositoryImpl();
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
      stmt.execute("DELETE FROM RESERVES");
      stmt.execute("DELETE FROM RESERVATION");
      stmt.execute("DELETE FROM CUSTOMER");
      stmt.execute("DELETE FROM TABLE_INFO");
    }
  }

  private void setupSampleData() throws SQLException {
    try (Connection conn = DatabaseUtil.getConnection();
         Statement stmt = conn.createStatement()) {
      stmt.execute("INSERT INTO CUSTOMER (Customer_ID, First_Name, " +
                   "Last_Name, Phone_Number, Email, Address) "
                   + "VALUES (1, 'John', 'Doe', '1234567890', " +
                     "'john.doe@example.com', '123 Main St')");
      stmt.execute("INSERT INTO RESERVATION (ReservationID, Customer_ID, " +
                   "Date, Time, NumberOfPeople) "
                   + "VALUES (1, 1, '2024-07-04', '18:00:00', 4), "
                   + "(2, 1, '2024-07-05', '19:00:00', 2), "
                   + "(3, 1, '2024-07-06', '20:00:00', 6)");
      stmt.execute(
          "INSERT INTO TABLE_INFO (Table_ID, Seating_Capacity, Table_Number) "
          + "VALUES (1, 4, 1), (2, 2, 2), (3, 6, 3)");
    }
  }

  @Test
  public void test_Insert() throws SQLException {
    ReservationTable reservation = new ReservationTable(2, 1);
    repository.insert(reservation);
    ReservationTable retrievedReservation = repository.findById(2, 1);
    assertNotNull(retrievedReservation, "Expected reservation to be retrieved");
    assertEquals(reservation.getReservationId(),
                 retrievedReservation.getReservationId());
    assertEquals(reservation.getTableId(), retrievedReservation.getTableId());
  }

  @Test
  public void test_FindById_NotFound() throws SQLException {
    ReservationTable retrievedReservation = repository.findById(999, 999);
    assertNull(retrievedReservation, "Expected no reservation to be retrieved");
  }

  @Test
  public void test_Delete() throws SQLException {
    ReservationTable reservation = new ReservationTable(3, 2);
    repository.insert(reservation);
    repository.delete(3, 2);
    ReservationTable deletedReservation = repository.findById(3, 2);
    assertNull(deletedReservation, "Expected reservation to be deleted");
  }

  @Test
  public void test_Update() throws SQLException {
    ReservationTable reservation = new ReservationTable(1, 3);
    repository.insert(reservation);
    reservation.setTableId(2);
    repository.update(reservation);
    ReservationTable updatedReservation = repository.findById(1, 2);
    assertNotNull(updatedReservation,
                  "Expected updated reservation to be retrieved");
    assertEquals(reservation.getReservationId(),
                 updatedReservation.getReservationId());
    assertEquals(2, updatedReservation.getTableId());
  }

  @Test
  public void test_FindAll() throws SQLException {
    ReservationTable reservation1 = new ReservationTable(3, 1);
    ReservationTable reservation2 = new ReservationTable(2, 2);
    repository.insert(reservation1);
    repository.insert(reservation2);
    List<ReservationTable> allReservations = repository.findAll();
    assertNotNull(allReservations,
                  "Expected list of reservations to be retrieved");
    assertEquals(2, allReservations.size(),
                 "Expected exactly two reservations");
  }
}
