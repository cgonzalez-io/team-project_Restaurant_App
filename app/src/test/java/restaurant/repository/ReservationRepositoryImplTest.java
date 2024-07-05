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
import restaurant.entity.Reservation;

public class ReservationRepositoryImplTest {

    private ReservationRepositoryImpl repository;

    // Functional test setup and teardown
    @BeforeEach
    public void setUp() throws SQLException {
        repository = new ReservationRepositoryImpl();
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
            // Delete all records from ReservationTable first to prevent foreign key constraint violations
            stmt.executeUpdate("DELETE FROM ReservationTable");

            // Now delete all records from Reservation
            stmt.executeUpdate("DELETE FROM Reservation");
        }
    }

    private void setupSampleData() throws SQLException {
        repository.insert(new Reservation(0, 1, Date.valueOf("2024-06-15"), Time.valueOf("18:00:00"), 4));
        repository.insert(new Reservation(0, 2, Date.valueOf("2024-06-16"), Time.valueOf("19:00:00"), 2));
        repository.insert(new Reservation(0, 3, Date.valueOf("2024-06-17"), Time.valueOf("20:00:00"), 6));
    }

    // Unit tests
    @Test
    public void unit_test_insert_valid_reservation() throws SQLException {
        ReservationRepositoryImpl reservationRepository = new ReservationRepositoryImpl();
        Reservation reservation = new Reservation(0, 1, Date.valueOf("2023-01-01"), Time.valueOf("18:00:00"), 4);

        try (MockedStatic<DatabaseUtil> mockedDatabaseUtil = Mockito.mockStatic(DatabaseUtil.class)) {
            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
            ResultSet mockGeneratedKeys = mock(ResultSet.class);

            when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockGeneratedKeys);
            when(mockGeneratedKeys.next()).thenReturn(true);
            when(mockGeneratedKeys.getInt(1)).thenReturn(1);

            reservationRepository.insert(reservation);

            verify(mockPreparedStatement).executeUpdate();
            assertEquals(1, reservation.getReservationID());
        }
    }


    @Test
    public void unit_test_update_valid_reservation() throws SQLException {
        ReservationRepositoryImpl reservationRepository = new ReservationRepositoryImpl();
        Reservation reservation = new Reservation(1, 1, Date.valueOf("2023-01-01"), Time.valueOf("18:00:00"), 4);

        try (MockedStatic<DatabaseUtil> mockedDatabaseUtil = Mockito.mockStatic(DatabaseUtil.class)) {
            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

            when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

            reservationRepository.update(reservation);

            verify(mockPreparedStatement).executeUpdate();
        }
    }

    @Test
    public void unit_test_find_by_id_existing_reservation() throws SQLException {
        ReservationRepositoryImpl reservationRepository = new ReservationRepositoryImpl();
        Reservation expectedReservation = new Reservation(1, 1, Date.valueOf("2023-01-01"), Time.valueOf("18:00:00"), 4);

        try (MockedStatic<DatabaseUtil> mockedDatabaseUtil = Mockito.mockStatic(DatabaseUtil.class)) {
            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
            ResultSet mockResultSet = mock(ResultSet.class);

            when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            when(mockResultSet.next()).thenReturn(true);
            when(mockResultSet.getInt("reservationID")).thenReturn(expectedReservation.getReservationID());
            when(mockResultSet.getInt("customerID")).thenReturn(expectedReservation.getCustomerID());
            when(mockResultSet.getDate("date")).thenReturn(expectedReservation.getDate());
            when(mockResultSet.getTime("time")).thenReturn(expectedReservation.getTime());
            when(mockResultSet.getInt("numberOfPeople")).thenReturn(expectedReservation.getNumberOfPeople());

            Reservation actualReservation = reservationRepository.findById(1);

            assertEquals(expectedReservation, actualReservation);
        }
    }

    @Test
    public void unit_test_find_by_id_non_existent_reservation() throws SQLException {
        ReservationRepositoryImpl reservationRepository = new ReservationRepositoryImpl();

        try (MockedStatic<DatabaseUtil> mockedDatabaseUtil = Mockito.mockStatic(DatabaseUtil.class)) {
            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
            ResultSet mockResultSet = mock(ResultSet.class);

            when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            when(mockResultSet.next()).thenReturn(false);

            Reservation actualReservation = reservationRepository.findById(999);

            assertNull(actualReservation);
        }
    }

    @Test
    public void unit_test_delete_existing_reservation() throws SQLException {
        ReservationRepositoryImpl reservationRepository = new ReservationRepositoryImpl();

        try (MockedStatic<DatabaseUtil> mockedDatabaseUtil = Mockito.mockStatic(DatabaseUtil.class)) {
            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

            when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

            reservationRepository.delete(1);

            verify(mockPreparedStatement).executeUpdate();
        }
    }

    @Test
    public void unit_test_find_all_reservations() throws SQLException {
        ReservationRepositoryImpl reservationRepository = new ReservationRepositoryImpl();
        List<Reservation> expectedReservations = List.of(
            new Reservation(1, 1, Date.valueOf("2023-01-01"), Time.valueOf("18:00:00"), 4),
            new Reservation(2, 2, Date.valueOf("2023-01-02"), Time.valueOf("19:00:00"), 2)
        );

        try (MockedStatic<DatabaseUtil> mockedDatabaseUtil = Mockito.mockStatic(DatabaseUtil.class)) {
            Connection mockConnection = mock(Connection.class);
            Statement mockStatement = mock(Statement.class);
            ResultSet mockResultSet = mock(ResultSet.class);

            when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
            when(mockConnection.createStatement()).thenReturn(mockStatement);
            when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

            when(mockResultSet.next()).thenReturn(true, true, false);
            when(mockResultSet.getInt("reservationID")).thenReturn(1, 2);
            when(mockResultSet.getInt("customerID")).thenReturn(1, 2);
            when(mockResultSet.getDate("date")).thenReturn(Date.valueOf("2023-01-01"), Date.valueOf("2023-01-02"));
            when(mockResultSet.getTime("time")).thenReturn(Time.valueOf("18:00:00"), Time.valueOf("19:00:00"));
            when(mockResultSet.getInt("numberOfPeople")).thenReturn(4, 2);

            List<Reservation> actualReservations = reservationRepository.findAll();

            assertEquals(expectedReservations, actualReservations);
        }
    }

    // Functional tests
    @Test
    public void func_test_insert_and_find_reservation() throws SQLException {
        // Arrange
        Reservation reservation = new Reservation(0, 1, Date.valueOf("2024-06-18"), Time.valueOf("18:00:00"), 4);

        repository.insert(reservation);

        // Act
        Reservation retrievedReservation = repository.findById(reservation.getReservationID());

        // Assert
        assertNotNull(retrievedReservation, "Expected reservation to be retrieved");
        assertEquals(reservation.getCustomerID(), retrievedReservation.getCustomerID());
        assertEquals(reservation.getDate(), retrievedReservation.getDate());
        assertEquals(reservation.getTime(), retrievedReservation.getTime());
        assertEquals(reservation.getNumberOfPeople(), retrievedReservation.getNumberOfPeople());
    }

    @Test
    public void func_test_insert_and_delete_reservation() throws SQLException {
        // Arrange
        Reservation reservation = new Reservation(0, 1, Date.valueOf("2024-06-18"), Time.valueOf("18:00:00"), 4);

        repository.insert(reservation);

        // Act
        repository.delete(reservation.getReservationID());
        Reservation deletedReservation = repository.findById(reservation.getReservationID());

        // Assert
        assertNull(deletedReservation, "Expected reservation to be deleted");
    }

    @Test
    public void func_test_find_all_reservations() throws SQLException {
        // Arrange
        Reservation reservation1 = new Reservation(0, 1, Date.valueOf("2024-06-18"), Time.valueOf("18:00:00"), 4);
        Reservation reservation2 = new Reservation(0, 2, Date.valueOf("2024-06-19"), Time.valueOf("19:00:00"), 2);

        repository.insert(reservation1);
        repository.insert(reservation2);

        // Act
        List<Reservation> reservations = repository.findAll();

        // Assert
        assertEquals(5, reservations.size()); // 3 existing + 2 new
    }
}
