package restaurant.entity;

/**
 * The RestaurantTable class represents a restaurant table object with their
 * unique ID, table number, and seating capacity.
 */
public class RestaurantTable {

  private int tableID;
  private int tableNumber;
  private int seatingCapacity;

  // Constructors
  public RestaurantTable(int tableID, int tableNumber, int seatingCapacity) {
    if (tableID < 0 || tableNumber < 0 || seatingCapacity < 0) {
      throw new IllegalArgumentException("Values cannot be negative");
    }
    this.tableID = tableID;
    this.tableNumber = tableNumber;
    this.seatingCapacity = seatingCapacity;
  }

  @Override
  public String toString() {
    return ("RestaurantTable{"
            + "tableID=" + tableID + ", tableNumber=" + tableNumber +
            ", seatingCapacity=" + seatingCapacity + '}');
  }

  // Getters and Setters
  public int getTableID() { return tableID; }

  public void setTableID(int tableID) {
    if (tableID < 0) {
      throw new IllegalArgumentException("Table ID cannot be negative.");
    }
    this.tableID = tableID;
  }
  public int getTableNumber() { return tableNumber; }

  public void setTableNumber(int tableNumber) {
    if (tableNumber < 0) {
      throw new IllegalArgumentException("Table number cannot be negative");
    }
    this.tableNumber = tableNumber;
  }

  public int getSeatingCapacity() { return seatingCapacity; }

  public void setSeatingCapacity(int seatingCapacity) {
    if (seatingCapacity < 0) {
      throw new IllegalArgumentException("Seating capacity cannot be negative");
    }
    this.seatingCapacity = seatingCapacity;
  }
}