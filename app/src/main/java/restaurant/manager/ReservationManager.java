package restaurant.manager;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import restaurant.entity.Reservation;
import restaurant.entity.RestaurantTable;
import restaurant.interfaces.ReservationRepository;
import restaurant.interfaces.RestaurantTableRepository;
import restaurant.repository.ReservationRepositoryImpl;
import restaurant.repository.RestaurantTableRepositoryImpl;

public class ReservationManager {

  private static Scanner scan = new Scanner(System.in);
  private static ReservationRepository reservationRepository =
      new ReservationRepositoryImpl();
  private static RestaurantTableRepository tableRepository =
      new RestaurantTableRepositoryImpl();

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
        System.out.println(
            "Please enter the reservation ID to be searched for:\n");
        int reservationID = scan.nextInt();
        scan.nextLine(); // consume newline
        searchReservation(reservationID);
        break;
      case "p":
        printAll();
        break;
      case "t":
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

  private static void printAll() throws SQLException {
    List<Reservation> reservations = reservationRepository.findAll();
    for (Reservation reservation : reservations) {
      System.out.println(reservation);
    }
  }

  private static void insert() throws SQLException {
    System.out.println("Enter reservation ID, customer ID,number of people, "
                       + "time (hh:mm:ss), and date (yyyy-mm-dd):");

    int reservationID = scan.nextInt();
    int customerID = scan.nextInt();
    int numberOfPeople = scan.nextInt();
    Time time = Time.valueOf(scan.next());
    Date date = Date.valueOf(scan.next());
    scan.nextLine();

    Reservation reservation =
        new Reservation(reservationID, customerID, date, time, numberOfPeople);
    reservationRepository.insert(reservation);
  }

  private static void update() throws SQLException {
    System.out.println("Enter the reservation ID to update:");
    int reservationID = scan.nextInt();
    scan.nextLine();

    System.out.println("Enter new customer ID, date (yyyy-mm-dd), time "
                       + "(hh:mm:ss), and number of people:");

    int customerID = scan.nextInt();
    Date date = Date.valueOf(scan.next());
    Time time = Time.valueOf(scan.next());
    int numberOfPeople = scan.nextInt();
    scan.nextLine();

    Reservation reservation =
        new Reservation(reservationID, customerID, date, time, numberOfPeople);
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
    scan.nextLine();

    reservationRepository.delete(reservationID);
  }

  private static void printAllTables() throws SQLException {
    List<RestaurantTable> tables = tableRepository.findAll();
    for (RestaurantTable table : tables) {
      System.out.println(table);
    }
  }

  public void showMenu() {
    String choice = "";

    do {
      System.out.println("Enter your selection\n");
      System.out.println("\ti: insert a new reservation\n");
      System.out.println("\tu: update an existing reservation\n");
      System.out.println("\td: delete a reservation\n");
      System.out.println("\ts: search for a reservation\n");
      System.out.println("\tp: print all reservations\n");
      System.out.println("\tt: print all tables\n");
      System.out.println("\tq: quit \n");
      choice = scan.nextLine();
      branching(choice);
    } while (!choice.toLowerCase().equals("q"));
  }
}
