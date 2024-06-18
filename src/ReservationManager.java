import entity.restaurant.Reservation;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import repository.entities.ReservationRepositoryImpl;
import repository.entities.interfaces.ReservationRepository;

public class ReservationManager {

    private static Scanner scan = new Scanner(System.in);
    private static ReservationRepository reservationRepository = new ReservationRepositoryImpl();

    public static void main(String[] args) {
        String choice = "";

        do {
            System.out.println("Enter your selection\n");
            System.out.println("\ti: insert a new reservation\n");
            System.out.println("\tu: update an existing reservation\n");
            System.out.println("\td: delete a reservation\n");
            System.out.println("\ts: search for a reservation\n");
            System.out.println("\tp: print all reservations\n");
            System.out.println("\tq: quit \n");
            choice = scan.nextLine();
            branching(choice);
        } while (!choice.toLowerCase().equals("q"));
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
                    deleteReservation();
                    break;
                case "s":
                    System.out.println("Please enter the reservation ID to be searched for:\n");
                    int reservationID = scan.nextInt();
                    scan.nextLine(); // consume newline
                    searchReservation(reservationID);
                    break;
                case "p":
                    printAll();
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

    private static void printAll() throws SQLException {
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    private static void insert() throws SQLException {
        System.out.println(
            "Enter reservation ID, customer ID, table ID, date (yyyy-mm-dd), time (hh:mm:ss), and number of people:"
        );

        int reservationID = scan.nextInt();
        int customerID = scan.nextInt();
        int tableID = scan.nextInt();
        Date date = Date.valueOf(scan.next());
        Time time = Time.valueOf(scan.next());
        int numberOfPeople = scan.nextInt();
        scan.nextLine(); // consume newline

        Reservation reservation = new Reservation(reservationID, customerID, tableID, date, time, numberOfPeople);
        reservationRepository.insert(reservation);
    }

    private static void update() throws SQLException {
        System.out.println("Enter the reservation ID to update:");
        int reservationID = scan.nextInt();
        scan.nextLine(); // consume newline

        System.out.println(
            "Enter new customer ID, table ID, date (yyyy-mm-dd), time (hh:mm:ss), and number of people:"
        );

        int customerID = scan.nextInt();
        int tableID = scan.nextInt();
        Date date = Date.valueOf(scan.next());
        Time time = Time.valueOf(scan.next());
        int numberOfPeople = scan.nextInt();
        scan.nextLine(); // consume newline

        Reservation reservation = new Reservation(reservationID, customerID, tableID, date, time, numberOfPeople);
        reservationRepository.update(reservation);
    }

    private static void searchReservation(int reservationID) throws SQLException {
        Reservation reservation = reservationRepository.findById(reservationID);
        if (reservation != null) {
            System.out.println(reservation);
        } else {
            System.out.println("The reservation ID does not exist.");
        }
    }

    private static void deleteReservation() throws SQLException {
        System.out.println("Enter the reservation ID to delete:");
        int reservationID = scan.nextInt();
        scan.nextLine(); // consume newline

        reservationRepository.delete(reservationID);
    }
}
