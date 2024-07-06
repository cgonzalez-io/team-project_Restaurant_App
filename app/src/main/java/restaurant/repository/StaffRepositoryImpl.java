package restaurant.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import restaurant.database.DatabaseUtil;
import restaurant.entity.Staff;
import restaurant.interfaces.StaffRepository;

public class StaffRepositoryImpl implements StaffRepository {

    @Override
    public void insert(Staff staff) throws SQLException {
        String sql =
            "INSERT INTO STAFF (First_Name, Last_Name, Role, Phone_Number, " +
            "Email, Address) VALUES (?, ?, ?, ?, ?, ?)";
        try (
            Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setString(1, staff.getFirstName());
            pstmt.setString(2, staff.getLastName());
            pstmt.setString(3, staff.getRole());
            pstmt.setString(4, staff.getPhone());
            pstmt.setString(5, staff.getEmail());
            pstmt.setString(6, staff.getAddress());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    staff.setStaffId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public void update(Staff staff) throws SQLException {
        String sql =
            "UPDATE STAFF SET First_Name = ?, Last_Name = ?, Role = ?, " +
            "Phone_Number = ?, Email = ?, Address = ? WHERE Staff_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, staff.getFirstName());
            pstmt.setString(2, staff.getLastName());
            pstmt.setString(3, staff.getRole());
            pstmt.setString(4, staff.getPhone());
            pstmt.setString(5, staff.getEmail());
            pstmt.setString(6, staff.getAddress());
            pstmt.setInt(7, staff.getStaffId());
            pstmt.executeUpdate();

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Staff with ID " + staff.getStaffId() + " does not exist.");
            }
        }
    }

    @Override
    public Staff findById(int staffId) throws SQLException {
        String sql = "SELECT * FROM STAFF WHERE Staff_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, staffId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Staff(
                        rs.getInt("Staff_ID"),
                        rs.getString("First_Name"),
                        rs.getString("Last_Name"),
                        rs.getString("Phone_Number"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getString("Role"),
                        null
                    );
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void delete(int staffId) throws SQLException {
        String sql = "DELETE FROM STAFF WHERE Staff_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, staffId);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Staff> findAll() throws SQLException {
        String sql = "SELECT * FROM STAFF";
        List<Staff> staffList = new ArrayList<>();
        try (
            Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                staffList.add(
                    new Staff(
                        rs.getInt("Staff_ID"),
                        rs.getString("First_Name"),
                        rs.getString("Last_Name"),
                        rs.getString("Phone_Number"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getString("Role"),
                        null
                    )
                );
            }
        }
        return staffList;
    }
}
