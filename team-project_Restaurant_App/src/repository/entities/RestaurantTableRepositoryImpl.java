package repository.entities;

import common.utilities.database.DatabaseUtil;
import entity.restaurant.RestaurantTable;
import repository.entities.interfaces.RestaurantTableRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantTableRepositoryImpl implements RestaurantTableRepository {

    @Override
    public void insert(RestaurantTable table) throws SQLException {
        String sql = "INSERT INTO RESTAURANTTABLE (TableID, TableNumber, SeatingCapacity) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, table.getTableID());
            pstmt.setInt(2, table.getTableNumber());
            pstmt.setInt(3, table.getSeatingCapacity());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void update(RestaurantTable table) throws SQLException {
        String sql = "UPDATE RESTAURANTTABLE SET TableNumber = ?, SeatingCapacity = ? WHERE TableID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, table.getTableNumber());
            pstmt.setInt(2, table.getSeatingCapacity());
            pstmt.setInt(3, table.getTableID());
            pstmt.executeUpdate();
        }
    }

    @Override
    public RestaurantTable findById(int tableID) throws SQLException {
        String sql = "SELECT * FROM RESTAURANTTABLE WHERE TableID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tableID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new RestaurantTable(
                        rs.getInt("TableID"),
                        rs.getInt("TableNumber"),
                        rs.getInt("SeatingCapacity")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void delete(int tableID) throws SQLException {
        String sql = "DELETE FROM RESTAURANTTABLE WHERE TableID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, tableID);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<RestaurantTable> findAll() throws SQLException {
        String sql = "SELECT * FROM RESTAURANTTABLE";
        List<RestaurantTable> tables = new ArrayList<>();
        try (
            Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                tables.add(
                    new RestaurantTable(
                        rs.getInt("TableID"),
                        rs.getInt("TableNumber"),
                        rs.getInt("SeatingCapacity")
                    )
                );
            }
        }
        return tables;
    }
}