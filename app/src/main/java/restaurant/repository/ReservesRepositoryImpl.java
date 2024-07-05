package restaurant.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import restaurant.database.DatabaseUtil;
import restaurant.entity.ReservationTable;
import restaurant.interfaces.ReservesRepository;

public class ReservesRepositoryImpl implements ReservesRepository {

  @Override
  public void insert(ReservationTable reserve) throws SQLException {
    String sql = "INSERT INTO RESERVES (Reservation_ID, Table_ID) VALUES (?,?)";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, reserve.getReservationId());
      pstmt.setInt(2, reserve.getTableId());
      pstmt.executeUpdate();
    }
  }

  @Override
  public void update(ReservationTable reserve) throws SQLException {
    String sql = "UPDATE RESERVES SET Table_ID = ? WHERE Reservation_ID = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, reserve.getTableId());
      pstmt.setInt(2, reserve.getReservationId());
      pstmt.executeUpdate();
    }
  }

  @Override
  public void delete(int reservationId, int tableId)throws SQLException {
    String sql = "DELETE FROM RESERVES WHERE Reservation_ID = ? AND Table_ID=?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, reservationId);
      pstmt.setInt(2, tableId);
      pstmt.executeUpdate();
    }
  }

  @Override
  public ReservationTable findById(int reservationId, int tableId)
      throws SQLException {
    String sql =
        "SELECT * FROM RESERVES WHERE Reservation_ID = ? AND Table_ID = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, reservationId);
      pstmt.setInt(2, tableId);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          return new ReservationTable(reservationId, tableId);
        } else {
          return null;
        }
      }
    }
  }

  @Override
  public List<ReservationTable> findAll() throws SQLException {
    String sql = "SELECT * FROM RESERVES";
    List<ReservationTable> reserves = new ArrayList<>();

    try (Connection conn = DatabaseUtil.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        int reservationId = rs.getInt("Reservation_ID");
        int tableId = rs.getInt("Table_ID");
        reserves.add(new ReservationTable(reservationId, tableId));
      }
    }
    return reserves;
  }
}