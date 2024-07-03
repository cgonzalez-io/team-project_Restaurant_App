package restaurant.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import restaurant.database.DatabaseUtil;
import restaurant.entity.StaffAssignment;
import restaurant.interfaces.StaffAssignmentRepository;

/**
 * The StaffAssignmentRepositoryImpl class implements the
 * StaffAssignmentRepository interface and provides methods to interact with a
 * database and perform CRUD operations on StaffAssignment objects.
 */
public class StaffAssignmentRepositoryImpl
    implements StaffAssignmentRepository {

  @Override
  public void insert(StaffAssignment staffAssignment) throws SQLException {
    String sql = "INSERT INTO StaffAssignment (StaffID, TableID, Date, Time) " +
                 "VALUES (?, ?, ?, ?)";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt =
             conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setInt(1, staffAssignment.getStaffID());
      pstmt.setInt(2, staffAssignment.getTableID());
      pstmt.setDate(3, staffAssignment.getDate());
      pstmt.setTime(4, staffAssignment.getTime());
      pstmt.executeUpdate();

      try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          staffAssignment.setAssignmentID(generatedKeys.getInt(1));
        }
      }
    }
  }

  @Override
  public void update(StaffAssignment staffAssignment) throws SQLException {
    String sql = "UPDATE StaffAssignment SET StaffID = ?, TableID = ?, Date " +
                 "= ?, Time = ? WHERE AssignmentID = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, staffAssignment.getStaffID());
      pstmt.setInt(2, staffAssignment.getTableID());
      pstmt.setDate(3, staffAssignment.getDate());
      pstmt.setTime(4, staffAssignment.getTime());
      pstmt.setInt(5, staffAssignment.getAssignmentID());
      pstmt.executeUpdate();
    }
  }

  @Override
  public StaffAssignment findById(int assignmentId) throws SQLException {
    String sql = "SELECT * FROM StaffAssignment WHERE AssignmentID = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, assignmentId);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          return new StaffAssignment(rs.getInt("AssignmentID"),
                                     rs.getInt("StaffID"), rs.getInt("TableID"),
                                     rs.getDate("Date"), rs.getTime("Time"));
        } else {
          return null;
        }
      }
    }
  }

  @Override
  public void delete(int assignmentId)throws SQLException {
    String sql = "DELETE FROM StaffAssignment WHERE AssignmentID = ?";
    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, assignmentId);
      pstmt.executeUpdate();
    }
  }

  @Override
  public List<StaffAssignment> findAll() throws SQLException {
    String sql = "SELECT * FROM StaffAssignment";
    List<StaffAssignment> staffAssignments = new ArrayList<>();
    try (Connection conn = DatabaseUtil.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        staffAssignments.add(new StaffAssignment(
            rs.getInt("AssignmentID"), rs.getInt("StaffID"),
            rs.getInt("TableID"), rs.getDate("Date"), rs.getTime("Time")));
      }
    }
    return staffAssignments;
  }
}
