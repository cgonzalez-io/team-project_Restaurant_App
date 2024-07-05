package restaurant.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import restaurant.database.DatabaseUtil;
import restaurant.entity.Customer;
import restaurant.interfaces.CustomerRepository;

/**
 * The CustomerRepositoryImpl class is an implementation of the
 * CustomerRepository interface. It provides methods to interact with the
 * database and perform CRUD operations on the Customer table.
 */
public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public void insert(Customer customer) throws SQLException {
        String sql =
            "INSERT INTO CUSTOMER (First_Name, Last_Name, Phone_Number, " + "Email, Address) VALUES (?, ?, ?, ?, ?)";
        try (
            Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
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
        String sql =
            "UPDATE CUSTOMER SET First_Name = ?, Last_Name = ?, " +
                "Phone_Number = ?, Email = ?, Address = ? WHERE Customer_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getPhone());
            pstmt.setString(4, customer.getEmail());
            pstmt.setString(5, customer.getAddress());
            pstmt.setInt(6, customer.getCustomerID());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Customer with ID " + customer.getCustomerID() + " does not exist.");
            }
        }
    }


    @Override
    public Customer findById(int customerId) throws SQLException {
        String sql = "SELECT * FROM CUSTOMER WHERE Customer_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getString("First_Name"),
                        rs.getString("Last_Name"),
                        rs.getString("Phone_Number"),
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
        String sql = "DELETE FROM CUSTOMER WHERE Customer_ID = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
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
                        rs.getString("Address"),
                        null
                    )
                );
            }
        }
        return customers;
    }
}
