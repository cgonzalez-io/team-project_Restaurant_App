package restaurant.interfaces;

import java.sql.SQLException;
import java.util.List;
import restaurant.entity.ReservationTable;
import restaurant.entity.RestaurantTable;

public interface ReservesRepository {
  void insert(ReservationTable reserve) throws SQLException;

  void update(ReservationTable reserve) throws SQLException;

  void delete(int reservationId, int tableId)throws SQLException;

  ReservationTable findById(int reservationId, int tableId) throws SQLException;

  List<ReservationTable> findAll() throws SQLException;
}