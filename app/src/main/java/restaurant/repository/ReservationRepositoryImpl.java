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
    String sql =
        "INSERT INTO Reservation (reservationID, customerID, tableID, date, " +
        "time, numberOfPeople) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, reservation.getReservationID());
      pstmt.setInt(2, reservation.getCustomerID());
      pstmt.setDate(4, reservation.getDate());
      pstmt.setTime(5, reservation.getTime());
      pstmt.setInt(6, reservation.getNumberOfPeople());
      pstmt.executeUpdate();
    }
  }

  @Override
  public void update(Reservation reservation) throws SQLException {
    String sql = "UPDATE Reservation SET customerID = ?, tableID = ?, date = " +
                 "?, time = ?, numberOfPeople = ? WHERE reservationID = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, reservation.getCustomerID());
      pstmt.setDate(3, reservation.getDate());
      pstmt.setTime(4, reservation.getTime());
      pstmt.setInt(5, reservation.getNumberOfPeople());
      pstmt.setInt(6, reservation.getReservationID());
      pstmt.executeUpdate();
    }
  }

  @Override
  public Reservation findById(int reservationID) throws SQLException {
    String sql = "SELECT * FROM Reservation WHERE reservationID = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, reservationID);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          return new Reservation(rs.getInt("reservationID"),
                                 rs.getInt("customerID"), rs.getDate("date"),
                                 rs.getTime("time"),
                                 rs.getInt("numberOfPeople"));
        } else {
          return null;
        }
      }
    }
  }

  @Override
  public void delete(int reservationID)throws SQLException {
    String sql = "DELETE FROM Reservation WHERE reservationID = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, reservationID);
      pstmt.executeUpdate();
    }
  }

  @Override
  public List<Reservation> findAll() throws SQLException {
    String sql = "SELECT * FROM Reservation";
    List<Reservation> reservations = new ArrayList<>();
    try (Connection conn = DatabaseUtil.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        reservations.add(new Reservation(rs.getInt("reservationID"),
                                         rs.getInt("customerID"),
                                         rs.getDate("date"), rs.getTime("time"),
                                         rs.getInt("numberOfPeople")));
      }
    }
    return reservations;
  }
}
