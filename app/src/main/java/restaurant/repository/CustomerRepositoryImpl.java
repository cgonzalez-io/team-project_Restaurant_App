package restaurant.repository;

import restaurant.database.DatabaseUtil;
import restaurant.entity.Customer;
import restaurant.interfaces.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public void insert(Customer customer) throws SQLException {
        String sql = "INSERT INTO CUSTOMER (First_Name, Last_Name, Phone_Number, Email, Address) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getPhone());
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
        String sql = "UPDATE CUSTOMER SET First_Name = ?, Last_Name = ?, Phone_Number = ?, Email = ?, Address = ? WHERE Customer_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getPhone());
            pstmt.setString(4, customer.getEmail());
            pstmt.setString(5, customer.getAddress());
            pstmt.setInt(6, customer.getCustomerID());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Customer with ID " + customer.getCustomerID() + " does not exist.");
            }
        }
    }
    @Override
    public Customer findById(int customerID) throws SQLException {
        String sql = "SELECT * FROM CUSTOMER WHERE Customer_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

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
    public List<Customer> findAll() throws SQLException {
        String sql = "SELECT * FROM CUSTOMER";
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                customers.add(new Customer(
                    rs.getInt("Customer_ID"),
                    rs.getString("First_Name"),
                    rs.getString("Last_Name"),
                    rs.getString("Phone_Number"),
                    rs.getString("Email"),
                    rs.getString("Address")
                ));
            }
        }
        return customers;
    }
    @Override
    public void delete(int customerID) throws SQLException {
        String sql = "DELETE FROM CUSTOMER WHERE Customer_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerID);
            pstmt.executeUpdate();
        }
    }
}
