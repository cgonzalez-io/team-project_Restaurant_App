package restaurant.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.entity.ReservationTable;
import restaurant.interfaces.ReservesRepository;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ReservesRepositoryImplTest {

    private ReservesRepository repository;

    @BeforeEach
    public void setUp() throws SQLException {

        // Initialize repository before each test
        repository = new ReservesRepositoryImpl();

        // Clean up existing data each time
        cleanUpDatabase();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Clean up after each test
        cleanUpDatabase();
    }

    private void cleanUpDatabase() throws SQLException {
        // Delete all data from RESERVES table
        List<ReservationTable> allReservations = repository.findAll();
        for (ReservationTable reservation : allReservations) {
            repository.delete(reservation.getReservationId(), reservation.getTableId());
        }
    }

    @Test
    public void test_Insert() throws SQLException {
        // Create a reservation to insert into RESERVES
        ReservationTable reservation = new ReservationTable(2, 1);

        // Perform insert
        repository.insert(reservation);

        // Verify by finding the inserted reservation
        ReservationTable retrievedReservation = repository.findById(2, 1);

        // Assert that retrievedReservation is not null
        assertNotNull(retrievedReservation, "Expected reservation to be retrieved");

        // Assert that retrievedReservation matches the inserted reservation
        assertEquals(reservation.getReservationId(), retrievedReservation.getReservationId());
        assertEquals(reservation.getTableId(), retrievedReservation.getTableId());
    }


    @Test
    public void test_FindById_NotFound() throws SQLException {
        // Perform findById for a non-existent reservation
        ReservationTable retrievedReservation = repository.findById(999, 999);

        // Assert that retrievedReservation is null
        assertNull(retrievedReservation, "Expected no reservation to be retrieved");
    }

    @Test
    public void test_Delete() throws SQLException {
        // Insert reservation
        ReservationTable reservation = new ReservationTable(3, 2); // Using existing Reservation_ID = 3
        repository.insert(reservation);

        // Perform delete
        repository.delete(3, 2);

        // Verify by trying to find the deleted reservation
        ReservationTable deletedReservation = repository.findById(3, 2);

        // Assert that deletedReservation is null after deletion
        assertNull(deletedReservation, "Expected reservation to be deleted");
    }

    @Test
    public void test_Update() throws SQLException {
        // Insert reservation
        ReservationTable reservation = new ReservationTable(1, 3);
        repository.insert(reservation);

        // Modify reservation
        reservation.setTableId(2);

        // Perform update
        repository.update(reservation);

        // Verify by finding the updated reservation
        ReservationTable updatedReservation = repository.findById(1, 2);

        // Assert that updatedReservation is not null
        assertNotNull(updatedReservation, "Expected updated reservation to be retrieved");

        // Assert that updatedReservation matches the modified reservation
        assertEquals(reservation.getReservationId(), updatedReservation.getReservationId());
        assertEquals(2, updatedReservation.getTableId());
    }


    @Test
    public void test_FindAll() throws SQLException {
        // Insert some reservations
        ReservationTable reservation1 = new ReservationTable(3, 1);
        ReservationTable reservation2 = new ReservationTable(2, 2);
        repository.insert(reservation1);
        repository.insert(reservation2);

        // Retrieve all reservations
        List<ReservationTable> allReservations = repository.findAll();

        // Assert that allReservations is not null
        assertNotNull(allReservations, "Expected list of reservations to be retrieved");
        assertEquals(2, allReservations.size(), "Expected exactly two reservations");

    }
}
