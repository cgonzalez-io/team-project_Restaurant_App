package repository.entities;

import common.utilities.database.DatabaseUtil;
import entity.restaurant.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import repository.entities.interfaces.CustomerRepository;

/**
 * The CustomerRepositoryImpl class is an implementation of the CustomerRepository interface.
 * It provides methods to interact with the database and perform CRUD operations on the Customer table.
 */
public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public void insert(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customer (FirstName, LastName, PhoneNumber, Email, Address) VALUES (?, ?, ?, ?, ?)";
        try (
            Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setInt(3, customer.getPhone());
            pstmt.setString(4, customer.getEmail());
            pstmt.setString(5, customer.getAddress());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setCustomerID(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public void update(Customer customer) throws SQLException {
        String sql =
            "UPDATE Customer SET FirstName = ?, LastName = ?, PhoneNumber = ?, Email = ?, Address = ? WHERE CustomerID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setInt(3, customer.getPhone());
            pstmt.setString(4, customer.getEmail());
            pstmt.setString(5, customer.getAddress());
            pstmt.setInt(6, customer.getCustomerID());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Customer findById(int customerId) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE CustomerID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        Integer.parseInt(rs.getString("PhoneNumber")),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        null
                    );
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void delete(int customerId) throws SQLException {
        String sql = "DELETE FROM Customer WHERE CustomerID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        String sql = "SELECT * FROM Customer";
        List<Customer> customers = new ArrayList<>();
        try (
            Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                customers.add(
                    new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        Integer.parseInt(rs.getString("PhoneNumber")),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        null
                    )
                );
            }
        }
        return customers;
    }
}
