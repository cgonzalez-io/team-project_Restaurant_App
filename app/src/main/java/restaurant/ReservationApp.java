package restaurant;

import java.util.Scanner;
import restaurant.manager.AssignmentManager;
import restaurant.manager.CustomerManager;
import restaurant.manager.ReservationManager;
import restaurant.manager.ReservesManager;
import restaurant.manager.StaffManager;
import restaurant.manager.TableManager;

public class ReservationApp {

  private static Scanner scan = new Scanner(System.in);
  private static ReservationManager reservationManager =
      new ReservationManager();
  private static CustomerManager customerManager = new CustomerManager();
  private static ReservesManager reservesManager = new ReservesManager();
  private static AssignmentManager assignmentManager = new AssignmentManager();
  private static StaffManager staffManager = new StaffManager();
  private static TableManager tableManager = new TableManager();

  public static void main(String[] args) {
    String choice = "";

    do {
      System.out.println("Enter your selection\n");
      System.out.println("\t1. Manage Reservation");
      System.out.println("\t2. Manage Customers");
      System.out.println("\t3. Manage Reservation Tables");
      System.out.println("\t4. Manage Staff Assignments");
      System.out.println("\t5. Manage Staff");
      System.out.println("\t6. Manage Tables");
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
        reservesManager.showMenu();
        break;
      case "4":
        assignmentManager.showMenu();
        break;
      case "5":
        staffManager.showMenu();
        break;
      case "6":
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
