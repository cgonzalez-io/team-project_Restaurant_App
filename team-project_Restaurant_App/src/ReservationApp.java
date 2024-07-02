import java.util.Scanner;

public class ReservationApp {

    private static Scanner scan = new Scanner(System.in);
    private static ReservationManager reservationManager = new ReservationManager();
    private static CustomerManager customerManager = new CustomerManager();
    private static TableManager tableManager = new TableManager();

    public static void main(String[] args) {
        String choice = "";

        do {
            System.out.println("Enter your selection\n");
            System.out.println("\t1. Manage Reservation");
            System.out.println("\t2. Manage Customers");
            System.out.println("\tq. Quit \n");
            choice = scan.nextLine();
            switch (choice) {
                case "1":
                    reservationManager.showMenu();
                    break;
                case "2":
                    customerManager.showMenu();
                    break;
                case "3":
                    tableManager.showMenu();
                    break;
                case "q":
                    break;
                default:
                    System.out.println("Invalid input\n");
            }
        } while (!choice.toLowerCase().equals("q"));

        scan.close();
    }
}