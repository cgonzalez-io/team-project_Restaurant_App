package restaurant.entity;

/**
 * The RestaurantTable class represents a restaurant table object with their unique ID, table number, and seating capacity.
 */
public class RestaurantTable {

    private int tableID;
    private int tableNumber;
    private int seatingCapacity;

    // Constructors
    public RestaurantTable(int tableID, int tableNumber, int seatingCapacity) {
        this.tableID = tableID;
        this.tableNumber = tableNumber;
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public String toString() {
        return (
            "RestaurantTable{" +
            "tableID=" +
            tableID +
            ", tableNumber=" +
            tableNumber +
            ", seatingCapacity=" +
            seatingCapacity +
            '}'
        );
    }

    // Getters and Setters
    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }
}
