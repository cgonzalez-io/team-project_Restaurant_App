package restaurant.manager;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import restaurant.entity.ReservationTable;
import restaurant.interfaces.ReservesRepository;
import restaurant.repository.ReservesRepositoryImpl;

public class ReservesManager {

    private static final Scanner scan = new Scanner(System.in);
    private static final ReservesRepository reservesRepository = new ReservesRepositoryImpl();

    public void showMenu() {
        String choice;

        do {
            System.out.println("Enter your selection\n");
            System.out.println("\ti: insert a new reservation table\n");
            System.out.println("\tu: update an existing reservation table\n");
            System.out.println("\td: delete a reservation table\n");
            System.out.println("\ts: search for a reservation table\n");
            System.out.println("\tp: print all reservation tables\n");
            System.out.println("\tq: quit \n");
            choice = scan.nextLine();
            branching(choice);
        } while (!choice.equalsIgnoreCase("q"));
    }

    private static void branching(String choice) {
        try {
            switch (choice) {
                case "i":
                    insert();
                    break;
                case "u":
                    update();
                    break;
                case "d":
                    deleteReservationTable();
                    break;
                case "s":
                    searchReservationTable();
                    break;
                case "p":
                    printAll();
                    break;
                default:
                    System.out.println("Invalid input\n");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void printAll() throws SQLException {
        List<ReservationTable> reserves = reservesRepository.findAll();
        if (reserves.isEmpty()) {
            System.out.println("No reservation tables found.");
        } else {
            for (ReservationTable reserve : reserves) {
                System.out.println(reserve);
            }
        }
    }

    private static void insert() throws SQLException {
        System.out.println("Enter reservation ID and table ID:");

        try {
            int reservationID = Integer.parseInt(scan.nextLine());
            int tableID = Integer.parseInt(scan.nextLine());
            ReservationTable reserve = new ReservationTable(reservationID, tableID);
            reservesRepository.insert(reserve);
            System.out.println("Reservation table inserted successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numbers.");
            scan.nextLine();
        }
    }

    private static void update() throws SQLException {
        System.out.println("Enter reservation ID to update:");
        try {
            int reservationID = Integer.parseInt(scan.nextLine());
            System.out.println("Enter new table ID:");
            int tableID = Integer.parseInt(scan.nextLine());
            ReservationTable reserve = new ReservationTable(reservationID, tableID);
            reservesRepository.update(reserve);
            System.out.println("Reservation table updated successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numbers.");
            scan.nextLine();
        }
    }

    private static void deleteReservationTable() throws SQLException {
        System.out.println("Enter reservation ID and table ID to delete:");

        try {
            int reservationID = Integer.parseInt(scan.nextLine());
            int tableID = Integer.parseInt(scan.nextLine());
            reservesRepository.delete(reservationID, tableID);
            System.out.println("Reservation table deleted successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numbers.");
            scan.nextLine();
        }
    }

    private static void searchReservationTable() throws SQLException {
        System.out.println("Enter reservation ID and table ID to search:");

        try {
            int reservationID = Integer.parseInt(scan.nextLine());
            int tableID = Integer.parseInt(scan.nextLine());
            ReservationTable reserve = reservesRepository.findById(reservationID, tableID);
            if (reserve != null) {
                System.out.println(reserve);
            } else {
                System.out.println("Reservation table not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numbers.");
            scan.nextLine();
        }
    }

    public static void main(String[] args) {
        ReservesManager manager = new ReservesManager();
        manager.showMenu();
    }
}
