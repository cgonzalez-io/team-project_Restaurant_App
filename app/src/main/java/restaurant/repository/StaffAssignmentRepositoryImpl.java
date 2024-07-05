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
public class StaffAssignmentRepositoryImpl implements StaffAssignmentRepository {

    @Override
    public void insert(StaffAssignment staffAssignment) throws SQLException {
        String sql = "INSERT INTO ASSIGNED_TO (Table_ID, Staff_ID, Date, Time) "
            + "VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, staffAssignment.getTableID());
            pstmt.setInt(2, staffAssignment.getStaffID());
            pstmt.setDate(3, staffAssignment.getDate());
            pstmt.setTime(4, staffAssignment.getTime());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void update(StaffAssignment staffAssignment) throws SQLException {
        String sql = "UPDATE ASSIGNED_TO SET Staff_ID = ?, Table_ID = ?, Date = ?, Time = ? "
            + "WHERE Table_ID = ? AND Staff_ID = ? AND Date = ? AND Time = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, staffAssignment.getStaffID());
            pstmt.setInt(2, staffAssignment.getTableID());
            pstmt.setDate(3, staffAssignment.getDate());
            pstmt.setTime(4, staffAssignment.getTime());
            pstmt.setInt(5, staffAssignment.getTableID());
            pstmt.setInt(6, staffAssignment.getStaffID());
            pstmt.setDate(7, staffAssignment.getDate());
            pstmt.setTime(8, staffAssignment.getTime());
            pstmt.executeUpdate();
        }
    }

    @Override
    public StaffAssignment findById(int tableID, int staffID, Date date, Time time) throws SQLException {
        String sql = "SELECT * FROM ASSIGNED_TO WHERE Table_ID = ? AND Staff_ID = ? AND Date = ? AND Time = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tableID);
            pstmt.setInt(2, staffID);
            pstmt.setDate(3, date);
            pstmt.setTime(4, time);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new StaffAssignment(rs.getInt("Table_ID"),
                        rs.getInt("Staff_ID"),
                        rs.getDate("Date"),
                        rs.getTime("Time"));
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void delete(int tableID, int staffID, Date date, Time time) throws SQLException {
        String sql = "DELETE FROM ASSIGNED_TO WHERE Table_ID = ? AND Staff_ID = ? AND Date = ? AND Time = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tableID);
            pstmt.setInt(2, staffID);
            pstmt.setDate(3, date);
            pstmt.setTime(4, time);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<StaffAssignment> findAll() throws SQLException {
        String sql = "SELECT * FROM ASSIGNED_TO";
        List<StaffAssignment> staffAssignments = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                staffAssignments.add(new StaffAssignment(
                    rs.getInt("Table_ID"),
                    rs.getInt("Staff_ID"),
                    rs.getDate("Date"),
                    rs.getTime("Time")));
            }
        }
        return staffAssignments;
    }
}
