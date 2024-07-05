package restaurant.interfaces;

import restaurant.entity.StaffAssignment;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public interface StaffAssignmentRepository {

    void insert(StaffAssignment staffAssignment) throws SQLException;

    void update(StaffAssignment staffAssignment) throws SQLException;

    StaffAssignment findById(int tableID, int staffID, Date date, Time time) throws SQLException;

    void delete(int tableID, int staffID, Date date, Time time) throws SQLException;

    List<StaffAssignment> findAll() throws SQLException;
}
