package restaurant.interfaces;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import restaurant.entity.StaffAssignment;

public interface StaffAssignmentRepository {

  void insert(StaffAssignment staffAssignment) throws SQLException;

  void update(StaffAssignment oldStaffAssignment, StaffAssignment newStaffAssignment) throws SQLException;

  StaffAssignment findById(int tableID, int staffID, Date date, Time time)
      throws SQLException;

  void delete(int tableID, int staffID, Date date, Time time)
      throws SQLException;

  List<StaffAssignment> findAll() throws SQLException;
}
