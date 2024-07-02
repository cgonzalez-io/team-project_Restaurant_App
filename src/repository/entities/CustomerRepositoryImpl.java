package repository.entities;

import common.utilities.database.DatabaseUtil;
import entity.restaurant.Customer;
import repository.entities.interfaces.CustomerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public void insert(Customer customer) throws SQLException {
        String sql = "INSERT INTO CUSTOMER (Customer_ID, First_Name, Last_Name, Phone_Number, Email, Address) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customer.getCustomerID());
            pstmt.setString(2, customer.getFirstName());
            pstmt.setString(3, customer.getLastName());
            pstmt.setString(4, customer.getPhoneNumber());
            pstmt.setString(5, customer.getEmail());
            pstmt.setString(6, customer.getAddress());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void update(Customer customer) throws SQLException {
        String sql =
            "UPDATE CUSTOMER SET First_Name = ?, Last_Name = ?, Phone_Number = ?, Email = ?, Address = ? WHERE Customer_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getPhoneNumber());
            pstmt.setString(4, customer.getEmail());
            pstmt.setString(5, customer.getAddress());
            pstmt.setInt(6, customer.getCustomerID());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Customer findById(int customerID) throws SQLException {
        String sql = "SELECT * FROM CUSTOMER WHERE Customer_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("First_Name"),
                        rs.getString("Last_Name"),
                        rs.getString("Phone_Number"),
                        rs.getString("Email"),
                        rs.getString("Address")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void delete(int customerID) throws SQLException {
        String sql = "DELETE FROM CUSTOMER WHERE Customer_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerID);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        String sql = "SELECT * FROM CUSTOMER";
        List<Customer> customers = new ArrayList<>();
        try (
            Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                customers.add(
                    new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("First_Name"),
                        rs.getString("Last_Name"),
                        rs.getString("Phone_Number"),
                        rs.getString("Email"),
                        rs.getString("Address")
                    )
                );
            }
        }
        return customers;
    }
}