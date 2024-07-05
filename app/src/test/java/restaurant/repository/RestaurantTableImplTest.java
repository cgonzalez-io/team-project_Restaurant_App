package restaurant.repository;

import org.junit.jupiter.api.*;
import restaurant.entity.RestaurantTable;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTableImplTest {

    private RestaurantTableRepositoryImpl repository;

    @BeforeEach
    public void setUp() throws SQLException {
        repository = new RestaurantTableRepositoryImpl();

        cleanUpDatabase();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        cleanUpDatabase();
    }

    private void cleanUpDatabase() throws SQLException {
        List<RestaurantTable> allTables = repository.findAll();
        for(RestaurantTable table : allTables) {
            repository.delete(table.getTableID());
        }
    }

    @Test
    public void test_Insert() throws SQLException {
        RestaurantTable table = new RestaurantTable(1, 2, 4);

        repository.insert(table);

        RestaurantTable retrievedTable = repository.findById(1);

        assertNotNull(retrievedTable, "Expected table to be retrieved");
        assertEquals(table.getTableID(), retrievedTable.getTableID());
    }

    @Test
    public void test_FindById_NotFound() throws SQLException {
        RestaurantTable retrievedTable = repository.findById(999);

        assertNull(retrievedTable, "Expected no table to be retrieved");
    }

    @Test
    public void test_Delete() throws SQLException {
        RestaurantTable table = new RestaurantTable(2, 2, 4);
        repository.insert(table);

        repository.delete(2);

        RestaurantTable deletedTable = repository.findById(2);

        assertNull(deletedTable, "Expected table to be deleted");
    }

    @Test
    public void test_Update() throws SQLException {
        RestaurantTable table = new RestaurantTable(3, 3, 4);
        repository.insert(table);

        table.setTableNumber(4);

        repository.update(table);

        RestaurantTable updatedTable = repository.findById(3);

        assertNotNull(updatedTable, "Expected updated table to be retrieved");
        assertEquals(table.getTableID(), updatedTable.getTableID());
        assertEquals(4, updatedTable.getTableNumber());
    }

    @Test
    public void test_FindAll() throws SQLException {
        RestaurantTable table1 = new RestaurantTable(4, 4, 4);
        RestaurantTable table2 = new RestaurantTable(5, 5, 5);
        repository.insert(table1);
        repository.insert(table2);

        List<RestaurantTable> allTables = repository.findAll();

        assertNotNull(allTables, "Expected list of tables to be retrieved");
        assertEquals(2, allTables.size(), "Expected exactly two tables");
    }
}