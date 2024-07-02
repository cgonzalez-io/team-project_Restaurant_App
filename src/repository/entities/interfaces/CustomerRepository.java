package repository.entities.interfaces;

import entity.restaurant.Customer;
import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository {
    void insert(Customer customer) throws SQLException;

    void update(Customer customer) throws SQLException;

    Customer findById(int customerId) throws SQLException;

    void delete(int customerId) throws SQLException;

    List<Customer> findAll() throws SQLException;
}
