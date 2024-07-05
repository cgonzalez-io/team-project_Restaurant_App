package restaurant.interfaces;

import java.sql.SQLException;
import java.util.List;
import restaurant.entity.StaffAssignment;

/**
 * The StaffAssignmentRepository interface provides methods to interact with a
 * database and perform CRUD operations on StaffAssignment objects.
 */
public interface StaffAssignmentRepository {
    void insert(StaffAssignment staffAssignment) throws SQLException;

    void update(StaffAssignment staffAssignment) throws SQLException;

    StaffAssignment findById(int assignmentId) throws SQLException;

    void delete(int assignmentId)throws SQLException;

    List<StaffAssignment> findAll() throws SQLException;
}