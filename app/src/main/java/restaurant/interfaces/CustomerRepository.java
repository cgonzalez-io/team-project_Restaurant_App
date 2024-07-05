package restaurant.interfaces;

import java.sql.SQLException;
import java.util.List;
import restaurant.entity.Customer;

public interface CustomerRepository {
    void insert(Customer customer) throws SQLException;

    void update(Customer customer) throws SQLException;

    Customer findById(int customerId) throws SQLException;

    void delete(int customerId)throws SQLException;

    List<Customer> findAll() throws SQLException;
}