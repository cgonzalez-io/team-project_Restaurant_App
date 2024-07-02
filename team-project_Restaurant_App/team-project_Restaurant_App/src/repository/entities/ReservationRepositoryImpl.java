package repository.entities;

import common.utilities.database.DatabaseUtil;
import entity.restaurant.Reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import repository.entities.interfaces.ReservationRepository;

/**
 * The ReservationRepositoryImpl class is an implementation of the ReservationRepository interface.
 * It provides methods to insert, update, delete, and find reservations in a database.
 */
public class ReservationRepositoryImpl implements ReservationRepository {

    @Override
    public void insert(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO RESERVATION (Reservation_ID, Customer_ID, Number_Of_People, Time, Date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservation.getReservationID());
            pstmt.setInt(2, reservation.getCustomerID());
            pstmt.setInt(3, reservation.getNumberOfPeople());
            pstmt.setTime(4, reservation.getTime());
            pstmt.setDate(5, reservation.getDate());
            pstmt.executeUpdate();
        }
    }


    @Override
    public void update(Reservation reservation) throws SQLException {
        String sql =
            "UPDATE RESERVATION SET Customer_ID = ?, Time = ?, Date = ?, Number_Of_People = ? WHERE Reservation_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservation.getCustomerID());
            pstmt.setTime(2, reservation.getTime());
            pstmt.setDate(3, reservation.getDate());
            pstmt.setInt(4, reservation.getNumberOfPeople());
            pstmt.setInt(5, reservation.getReservationID());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Reservation findById(int reservationID) throws SQLException {
        String sql = "SELECT * FROM RESERVATION WHERE Reservation_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservationID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Reservation(
                        rs.getInt("Reservation_ID"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("Number_Of_People"),
                        rs.getTime("Time"),
                        rs.getDate("Date")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void delete(int reservationID) throws SQLException {
        String sql = "DELETE FROM RESERVATION WHERE Reservation_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservationID);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Reservation> findAll() throws SQLException {
        String sql = "SELECT * FROM RESERVATION";
        List<Reservation> reservations = new ArrayList<>();
        try (
            Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                reservations.add(
                    new Reservation(
                        rs.getInt("Reservation_ID"),
                        rs.getInt("Customer_ID"),
                        rs.getInt("Number_Of_People"),
                        rs.getTime("Time"),
                        rs.getDate("Date")
                    )
                );
            }
        }
        return reservations;
    }
}
