package repository.entities.interfaces;

import entity.restaurant.Customer;
import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository {
    void insert(Customer customer) throws SQLException;
    void update(Customer customer) throws SQLException;
    Customer findById(int customerID) throws SQLException;
    void delete(int customerID) throws SQLException;
    List<Customer> findAll() throws SQLException;

}
