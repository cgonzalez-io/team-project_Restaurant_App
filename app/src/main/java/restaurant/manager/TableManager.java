package restaurant.manager;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import restaurant.entity.RestaurantTable;
import restaurant.interfaces.RestaurantTableRepository;
import restaurant.repository.RestaurantTableRepositoryImpl;

public class TableManager {

    private static Scanner scan = new Scanner(System.in);
    private static RestaurantTableRepository tableRepository = new RestaurantTableRepositoryImpl();

    public void showMenu() {
        String choice = "";

        do {
            System.out.println("Enter your selection\n");
            System.out.println("\ti: insert a new table\n");
            System.out.println("\tu: update an existing table\n");
            System.out.println("\td: delete a table\n");
            System.out.println("\ts: search for a table\n");
            System.out.println("\tp: print all tables\n");
            System.out.println("\tq: quit \n");
            choice = scan.nextLine();
            branching(choice);
        } while (!choice.toLowerCase().equals("q"));
    }

    private void branching(String choice) {
        try {
            switch (choice) {
                case "i":
                    insertTable();
                    break;
                case "u":
                    updateTable();
                    break;
                case "d":
                    deleteTable();
                    break;
                case "s":
                    System.out.println("Please enter the table ID to be searched for:\n");
                    int tableID = scan.nextInt();
                    scan.nextLine();
                    searchTable(tableID);
                    break;
                case "p":
                    printAllTables();
                    break;
                default:
                    System.out.println("Invalid input\n");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Invalid input.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertTable() throws SQLException {
        System.out.println("Please enter a table ID, table number and seating capacity:");
        int tableID = scan.nextInt();
        int tableNumber = scan.nextInt();
        int seatingCapacity = scan.nextInt();
        scan.nextLine();

        RestaurantTable newTable = new RestaurantTable(tableID, tableNumber, seatingCapacity);
        tableRepository.insert(newTable);
    }

    private void updateTable() throws SQLException {
        System.out.println("Please enter the Table ID to be updated:");
        int tableID = scan.nextInt();
        System.out.println("Please enter the updated table number and seating capacity:");
        int tableNumber = scan.nextInt();
        int seatingCapacity = scan.nextInt();
        scan.nextLine();
        RestaurantTable updatedTable = new RestaurantTable(tableID, tableNumber, seatingCapacity);
        tableRepository.update(updatedTable);
    }

    private void deleteTable() throws SQLException {
        System.out.println("Please enter the table ID to be deleted:");
        int tableID = scan.nextInt();
        scan.nextLine();
        tableRepository.delete(tableID);
    }

    private void searchTable(int tableID) throws SQLException {
        RestaurantTable table = tableRepository.findById(tableID);
        if (table != null) {
            System.out.println("Found Table: " + table);
        } else {
            System.out.println("Table not found.");
        }
    }

    private void printAllTables() throws SQLException {
        List<RestaurantTable> tables = tableRepository.findAll();
        for (RestaurantTable table : tables) {
            System.out.println(table);
        }
    }
}
