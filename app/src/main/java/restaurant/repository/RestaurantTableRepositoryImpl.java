package restaurant.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import restaurant.database.DatabaseUtil;
import restaurant.entity.RestaurantTable;
import restaurant.interfaces.RestaurantTableRepository;

public class RestaurantTableRepositoryImpl implements RestaurantTableRepository {

    @Override
    public void insert(RestaurantTable table) throws SQLException {
        String sql = "INSERT INTO TABLE_INFO (Table_ID, Table_Number, " + "Seating_Capacity) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, table.getTableID());
            pstmt.setInt(2, table.getTableNumber());
            pstmt.setInt(3, table.getSeatingCapacity());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void update(RestaurantTable table) throws SQLException {
        String sql = "UPDATE TABLE_INFO SET Table_Number = ?, Seating_Capacity = " + "? WHERE Table_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, table.getTableNumber());
            pstmt.setInt(2, table.getSeatingCapacity());
            pstmt.setInt(3, table.getTableID());
            pstmt.executeUpdate();

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Table with ID " + table.getTableID() + " does not exist.");
            }
        }
    }

    @Override
    public RestaurantTable findById(int tableID) throws SQLException {
        String sql = "SELECT * FROM TABLE_INFO WHERE Table_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tableID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new RestaurantTable(
                        rs.getInt("Table_ID"),
                        rs.getInt("Table_Number"),
                        rs.getInt("Seating_Capacity")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void delete(int tableID) throws SQLException {
        String sql = "DELETE FROM TABLE_INFO WHERE Table_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tableID);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<RestaurantTable> findAll() throws SQLException {
        String sql = "SELECT * FROM TABLE_INFO";
        List<RestaurantTable> tables = new ArrayList<>();
        try (
            Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                tables.add(
                    new RestaurantTable(rs.getInt("Table_ID"), rs.getInt("Table_Number"), rs.getInt("Seating_Capacity"))
                );
            }
        }
        return tables;
    }
}
