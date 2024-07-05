package restaurant.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import restaurant.database.DatabaseUtil;
import restaurant.entity.StaffAssignment;

public class StaffAssignmentRepositoryImplTest {

    private StaffAssignmentRepositoryImpl repository;

    @BeforeEach
    public void setUp() throws SQLException {
        repository = new StaffAssignmentRepositoryImpl();
        cleanUpDatabase();
        setupSampleData();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        cleanUpDatabase();
    }

    private void cleanUpDatabase() throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM ASSIGNED_TO");
            stmt.executeUpdate("DELETE FROM TABLE_INFO");
        }
    }

    private void setupSampleData() throws SQLException {
        // Insert sample tables into TABLE_INFO
        try (Connection conn = DatabaseUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("INSERT INTO TABLE_INFO (Table_ID, Table_Number) VALUES (1, '1')");
            stmt.executeUpdate("INSERT INTO TABLE_INFO (Table_ID, Table_Number) VALUES (2, '2')");
            stmt.executeUpdate("INSERT INTO TABLE_INFO (Table_ID, Table_Number) VALUES (3, '3')");
        }

        // Insert sample staff assignments
        repository.insert(new StaffAssignment(1, 1, Date.valueOf("2024-06-15"), Time.valueOf("18:00:00")));
        repository.insert(new StaffAssignment(2, 2, Date.valueOf("2024-06-16"), Time.valueOf("19:00:00")));
        repository.insert(new StaffAssignment(3, 3, Date.valueOf("2024-06-17"), Time.valueOf("20:00:00")));
    }

    @Test
    public void unit_test_insert_valid_assignment() throws SQLException {
        StaffAssignment assignment = new StaffAssignment(1, 1, Date.valueOf("2023-01-01"), Time.valueOf("18:00:00"));

        try (MockedStatic<DatabaseUtil> mockedDatabaseUtil = Mockito.mockStatic(DatabaseUtil.class)) {
            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

            when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

            repository.insert(assignment);

            verify(mockPreparedStatement).executeUpdate();
        }
    }

    @Test
    public void unit_test_update_valid_assignment() throws SQLException {
        StaffAssignment assignment = new StaffAssignment(1, 1, Date.valueOf("2023-01-01"), Time.valueOf("18:00:00"));

        try (MockedStatic<DatabaseUtil> mockedDatabaseUtil = Mockito.mockStatic(DatabaseUtil.class)) {
            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

            when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

            repository.update(assignment);

            verify(mockPreparedStatement).executeUpdate();
        }
    }

    @Test
    public void unit_test_find_by_id_existing_assignment() throws SQLException {
        StaffAssignment expectedAssignment = new StaffAssignment(1, 1, Date.valueOf("2023-01-01"), Time.valueOf("18:00:00"));

        try (MockedStatic<DatabaseUtil> mockedDatabaseUtil = Mockito.mockStatic(DatabaseUtil.class)) {
            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
            ResultSet mockResultSet = mock(ResultSet.class);

            when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            when(mockResultSet.next()).thenReturn(true);
            when(mockResultSet.getInt("Table_ID")).thenReturn(expectedAssignment.getTableID());
            when(mockResultSet.getInt("Staff_ID")).thenReturn(expectedAssignment.getStaffID());
            when(mockResultSet.getDate("Date")).thenReturn(expectedAssignment.getDate());
            when(mockResultSet.getTime("Time")).thenReturn(expectedAssignment.getTime());

            StaffAssignment actualAssignment = repository.findById(1, 1, Date.valueOf("2023-01-01"), Time.valueOf("18:00:00"));

            assertEquals(expectedAssignment, actualAssignment);
        }
    }

    @Test
    public void unit_test_find_by_id_non_existent_assignment() throws SQLException {
        try (MockedStatic<DatabaseUtil> mockedDatabaseUtil = Mockito.mockStatic(DatabaseUtil.class)) {
            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
            ResultSet mockResultSet = mock(ResultSet.class);

            when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
            when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            when(mockResultSet.next()).thenReturn(false);

            StaffAssignment actualAssignment = repository.findById(999, 999, Date.valueOf("2023-01-01"), Time.valueOf("18:00:00"));

            assertNull(actualAssignment);
        }
    }

    @Test
    public void unit_test_delete_existing_assignment() throws SQLException {
        try (MockedStatic<DatabaseUtil> mockedDatabaseUtil = Mockito.mockStatic(DatabaseUtil.class)) {
            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

            when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

            repository.delete(1, 1, Date.valueOf("2023-01-01"), Time.valueOf("18:00:00"));

            verify(mockPreparedStatement).executeUpdate();
        }
    }

    @Test
    public void unit_test_find_all_assignments() throws SQLException {
        List<StaffAssignment> expectedAssignments = List.of(
            new StaffAssignment(1, 1, Date.valueOf("2023-01-01"), Time.valueOf("18:00:00")),
            new StaffAssignment(2, 2, Date.valueOf("2023-01-02"), Time.valueOf("19:00:00"))
        );

        try (MockedStatic<DatabaseUtil> mockedDatabaseUtil = Mockito.mockStatic(DatabaseUtil.class)) {
            Connection mockConnection = mock(Connection.class);
            Statement mockStatement = mock(Statement.class);
            ResultSet mockResultSet = mock(ResultSet.class);

            when(DatabaseUtil.getConnection()).thenReturn(mockConnection);
            when(mockConnection.createStatement()).thenReturn(mockStatement);
            when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

            when(mockResultSet.next()).thenReturn(true, true, false);
            when(mockResultSet.getInt("Table_ID")).thenReturn(1, 2);
            when(mockResultSet.getInt("Staff_ID")).thenReturn(1, 2);
            when(mockResultSet.getDate("Date")).thenReturn(Date.valueOf("2023-01-01"), Date.valueOf("2023-01-02"));
            when(mockResultSet.getTime("Time")).thenReturn(Time.valueOf("18:00:00"), Time.valueOf("19:00:00"));

            List<StaffAssignment> actualAssignments = repository.findAll();

            assertEquals(expectedAssignments, actualAssignments);
        }
    }

    @Test
    public void func_test_insert_and_find_assignment() throws SQLException {
        // Ensure the table exists before inserting an assignment
        try (Connection conn = DatabaseUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("INSERT INTO TABLE_INFO (Table_ID, Table_Number) VALUES (4, '1')");
        }

        // Arrange
        StaffAssignment assignment = new StaffAssignment(1, 1, Date.valueOf("2024-06-18"), Time.valueOf("18:00:00"));

        repository.insert(assignment);

        // Act
        StaffAssignment retrievedAssignment = repository.findById(assignment.getTableID(), assignment.getStaffID(), assignment.getDate(), assignment.getTime());

        // Assert
        assertNotNull(retrievedAssignment, "Expected assignment to be retrieved");
        assertEquals(assignment.getTableID(), retrievedAssignment.getTableID());
        assertEquals(assignment.getStaffID(), retrievedAssignment.getStaffID());
        assertEquals(assignment.getDate(), retrievedAssignment.getDate());
        assertEquals(assignment.getTime(), retrievedAssignment.getTime());
    }

    @Test
    public void func_test_insert_and_delete_assignment() throws SQLException {
        // Ensure the table exists before inserting an assignment
        try (Connection conn = DatabaseUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("INSERT INTO TABLE_INFO (Table_ID, Table_Number) VALUES (4, '1')");
        }

        // Arrange
        StaffAssignment assignment = new StaffAssignment(1, 1, Date.valueOf("2024-06-18"), Time.valueOf("18:00:00"));

        repository.insert(assignment);

        // Act
        repository.delete(assignment.getTableID(), assignment.getStaffID(), assignment.getDate(), assignment.getTime());
        StaffAssignment deletedAssignment = repository.findById(assignment.getTableID(), assignment.getStaffID(), assignment.getDate(), assignment.getTime());

        // Assert
        assertNull(deletedAssignment, "Expected assignment to be deleted");
    }

    @Test
    public void func_test_find_all_assignments() throws SQLException {
        // Ensure the tables exist before inserting assignments
        try (Connection conn = DatabaseUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("INSERT INTO TABLE_INFO (Table_ID, Table_Number) VALUES (4, '1')");
            stmt.executeUpdate("INSERT INTO TABLE_INFO (Table_ID, Table_Number) VALUES (5, '2')");
        }

        // Arrange
        StaffAssignment assignment1 = new StaffAssignment(1, 1, Date.valueOf("2024-06-18"), Time.valueOf("18:00:00"));
        StaffAssignment assignment2 = new StaffAssignment(2, 2, Date.valueOf("2024-06-19"), Time.valueOf("19:00:00"));

        repository.insert(assignment1);
        repository.insert(assignment2);

        // Act
        List<StaffAssignment> assignments = repository.findAll();

        // Assert
        assertEquals(5, assignments.size()); // 3 existing + 2 new
    }
}
