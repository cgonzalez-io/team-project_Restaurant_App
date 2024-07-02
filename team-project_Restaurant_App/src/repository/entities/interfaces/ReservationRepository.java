package repository.entities.interfaces;

import entity.restaurant.Reservation;
import java.sql.SQLException;
import java.util.List;

public interface ReservationRepository {
    void insert(Reservation reservation) throws SQLException;
    void update(Reservation reservation) throws SQLException;
    Reservation findById(int reservationID) throws SQLException;
    void delete(int reservationID) throws SQLException;
    List<Reservation> findAll() throws SQLException;
}
