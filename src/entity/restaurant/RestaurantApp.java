package entity.restaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class RestaurantApp {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: java RestaurantApp <url> <username> <password> <driver>");
            System.exit(1);
        }

        String url = args[0];
        String user = args[1];
        String password = args[2];
        String driver = args[3];

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database.");

            while (true) {
                showMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        DBManager.insertCustomer(connection);
                        break;
                    case 2:
                        DBManager.viewReservationsForDate(connection);
                        break;
                    case 3:
                        DBManager.deleteReservation(connection);
                        break;
                    case 4:
                        DBManager.listStaffAssignments(connection);
                        break;
                    case 5:
                        DBManager.updateReservation(connection);
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Database driver class not found.");
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        System.out.println("Restaurant Management System");
        System.out.println("1. Insert a new customer");
        System.out.println("2. View reservations for a specific date");
        System.out.println("3. Delete a reservation based on date and time");
        System.out.println("4. List all staff assignments");
        System.out.println("5. Update reservation information");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

}


