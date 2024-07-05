package restaurant.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import restaurant.database.DatabaseUtil;
import restaurant.entity.Reservation;
import restaurant.interfaces.ReservationRepository;

/**
 * The ReservationRepositoryImpl class is an implementation of the
 * ReservationRepository interface. It provides methods to insert, update,
 * delete, and find reservations in a database.
 */
public class ReservationRepositoryImpl implements ReservationRepository {

    @Override
    public void insert(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO RESERVATION (Customer_ID, Date, Time, NumberOfPeople) VALUES (?, ?, ?, ?)";
        try (
            Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setInt(1, reservation.getCustomerID());
            pstmt.setDate(2, reservation.getDate());
            pstmt.setTime(3, reservation.getTime());
            pstmt.setInt(4, reservation.getNumberOfPeople());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservation.setReservationID(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public void update(Reservation reservation) throws SQLException {
        String sql =
            "UPDATE RESERVATION SET Customer_ID = ?, Date = ?, Time = ?, NumberOfPeople = ? WHERE ReservationID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservation.getCustomerID());
            pstmt.setDate(2, reservation.getDate());
            pstmt.setTime(3, reservation.getTime());
            pstmt.setInt(4, reservation.getNumberOfPeople());
            pstmt.setInt(5, reservation.getReservationID());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Reservation findById(int reservationID) throws SQLException {
        String sql = "SELECT * FROM RESERVATION WHERE ReservationID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservationID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Reservation(
                        rs.getInt("ReservationID"),
                        rs.getInt("Customer_ID"),
                        rs.getDate("Date"),
                        rs.getTime("Time"),
                        rs.getInt("NumberOfPeople")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void delete(int reservationID) throws SQLException {
        String sql = "DELETE FROM RESERVATION WHERE ReservationID = ?";
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
                        rs.getInt("ReservationID"),
                        rs.getInt("Customer_ID"),
                        rs.getDate("Date"),
                        rs.getTime("Time"),
                        rs.getInt("NumberOfPeople")
                    )
                );
            }
        }
        return reservations;
    }
}
