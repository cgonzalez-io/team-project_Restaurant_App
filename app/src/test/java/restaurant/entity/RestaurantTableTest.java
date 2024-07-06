package restaurant.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RestaurantTableTest {

  @Test
  public void test_table_with_valid_inputs() {
    RestaurantTable table = new RestaurantTable(1, 10, 4);

    assertEquals(1, table.getTableID());
    assertEquals(10, table.getTableNumber());
    assertEquals(4, table.getSeatingCapacity());
  }

  @Test
  public void test_set_valid_table_values() {
    RestaurantTable table = new RestaurantTable(1, 10, 4);

    table.setTableID(2);
    table.setTableNumber(11);
    table.setSeatingCapacity(5);

    assertEquals(2, table.getTableID());
    assertEquals(11, table.getTableNumber());
    assertEquals(5, table.getSeatingCapacity());
  }

  @Test
  public void test_returns_correct_tableID() {
    RestaurantTable table = new RestaurantTable(123, 10, 4);

    assertEquals(123, table.getTableID());
  }

  @Test
  public void test_correct_string_format() {
    RestaurantTable table = new RestaurantTable(1, 10, 4);

    String expected =
        "RestaurantTable{tableID=1, tableNumber=10, seatingCapacity=4}";

    assertEquals(expected, table.toString());
  }

  @Test
  public void test_equals_same() {
    RestaurantTable table = new RestaurantTable(1, 10, 4);

    assertTrue(table.equals(table));
  }

  @Test
  public void test_equals_different_tableIDs() {
    RestaurantTable table1 = new RestaurantTable(1, 10, 4);
    RestaurantTable table2 = new RestaurantTable(2, 10, 4);

    assertFalse(table1.equals(table2));
  }

  @Test
  public void test_hashCode() {
    RestaurantTable table = new RestaurantTable(1, 10, 4);

    int initialHashCode = table.hashCode();

    assertEquals(initialHashCode, table.hashCode());
    assertEquals(initialHashCode, table.hashCode());
  }

  @Test
  public void test_hashCode_tableID() {
    RestaurantTable table = new RestaurantTable(0, 10, 4);

    int hashCode = table.hashCode();

    assertNotNull(hashCode);
  }

  @Test
  public void test_table_setter() {
    RestaurantTable table = new RestaurantTable(0, 0, 0);

    int validTableID = 123;
    int validTableNumber = 12;
    int validSeatingCapacity = 4;

    table.setTableID(validTableID);
    table.setTableNumber(validTableNumber);
    table.setSeatingCapacity(validSeatingCapacity);

    assertEquals(validTableID, table.getTableID());
    assertEquals(validTableNumber, table.getTableNumber());
    assertEquals(validSeatingCapacity, table.getSeatingCapacity());
  }

  @Test
  public void test_table_with_zero_seating_capacity() {
    RestaurantTable restaurantTable = new RestaurantTable(101, 12, 0);

    assertEquals(101, restaurantTable.getTableID());
    assertEquals(12, restaurantTable.getTableNumber());
    assertEquals(0, restaurantTable.getSeatingCapacity());
  }

  @Test
  public void test_set_and_get_maximum_integer_values() {
    RestaurantTable restaurantTable = new RestaurantTable(
        Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

    assertEquals(Integer.MAX_VALUE, restaurantTable.getTableID());
    assertEquals(Integer.MAX_VALUE, restaurantTable.getTableNumber());
    assertEquals(Integer.MAX_VALUE, restaurantTable.getSeatingCapacity());
  }

  @Test
  public void test_set_and_get_minimum_integer_values() {
    RestaurantTable restaurantTable = new RestaurantTable(0, 0, 0);

    assertEquals(0, restaurantTable.getTableID());
    assertEquals(0, restaurantTable.getTableNumber());
    assertEquals(0, restaurantTable.getSeatingCapacity());
  }

  @Test
  public void test_set_and_get_negative_values() {
    assertThrows(IllegalArgumentException.class,
                 () -> new RestaurantTable(-101, -12, -4));
  }

  @Test
  public void test_set_negative_tableId() {
    RestaurantTable restaurantTable = new RestaurantTable(101, 12, 4);
    assertThrows(IllegalArgumentException.class,
                 () -> restaurantTable.setTableID(-101));
  }

  @Test
  public void test_set_negative_tableNumber() {
    RestaurantTable restaurantTable = new RestaurantTable(101, 12, 4);
    assertThrows(IllegalArgumentException.class,
                 () -> restaurantTable.setTableNumber(-12));
  }

  @Test
  public void test_set_negative_seatingCapacity() {
    RestaurantTable restaurantTable = new RestaurantTable(101, 12, 4);
    assertThrows(IllegalArgumentException.class,
                 () -> restaurantTable.setSeatingCapacity(-4));
  }
}
