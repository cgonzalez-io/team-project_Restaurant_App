package restaurant.interfaces;

import java.sql.SQLException;
import java.util.List;
import restaurant.entity.RestaurantTable;

public interface RestaurantTableRepository {
  void insert(RestaurantTable table) throws SQLException;

  void update(RestaurantTable table) throws SQLException;

  RestaurantTable findById(int tableID) throws SQLException;

  void delete(int tableID)throws SQLException;

  List<RestaurantTable> findAll() throws SQLException;
}