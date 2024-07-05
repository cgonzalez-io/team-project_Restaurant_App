package restaurant.manager;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;
import restaurant.entity.StaffAssignment;
import restaurant.interfaces.StaffAssignmentRepository;
import restaurant.repository.StaffAssignmentRepositoryImpl;

public class AssignmentManager {

  private static final Scanner scan = new Scanner(System.in);
  private static final StaffAssignmentRepository repository =
      new StaffAssignmentRepositoryImpl();

  public void showMenu() {
    String choice;

    do {
      System.out.println("Enter your selection\n");
      System.out.println("\ti: insert a new staff assignment\n");
      System.out.println("\tu: update an existing staff assignment\n");
      System.out.println("\td: delete a staff assignment\n");
      System.out.println("\ts: search for a staff assignment\n");
      System.out.println("\tp: print all staff assignments\n");
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
        deleteAssignment();
        break;
      case "s":
        searchAssignment();
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
    List<StaffAssignment> assignments = repository.findAll();
    if (assignments.isEmpty()) {
      System.out.println("No staff assignments found.");
    } else {
      for (StaffAssignment assignment : assignments) {
        System.out.println(assignment);
      }
    }
  }

  private static void insert() throws SQLException {
    System.out.println(
        "Enter Staff ID, Table ID, Date (yyyy-mm-dd), and Time (hh:mm:ss):");

    try {
      int staffID = Integer.parseInt(scan.nextLine());
      int tableID = Integer.parseInt(scan.nextLine());
      Date date = Date.valueOf(scan.nextLine());
      Time time = Time.valueOf(scan.nextLine());
      StaffAssignment assignment =
          new StaffAssignment(staffID, tableID, date, time);
      repository.insert(assignment);
      System.out.println("Staff assignment inserted successfully.");
    } catch (NumberFormatException e) {
      System.out.println("Invalid input. Please enter valid data.");
      scan.nextLine();
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid input. Please enter valid data.");
      scan.nextLine();
    }
  }

  private static void update() throws SQLException {
    System.out.println("Enter existing Staff ID, Table ID, Date "
                       + "(yyyy-mm-dd), and Time (hh:mm:ss) to update:");
    try {
      int staffID = Integer.parseInt(scan.nextLine());
      int tableID = Integer.parseInt(scan.nextLine());
      Date date = Date.valueOf(scan.nextLine());
      Time time = Time.valueOf(scan.nextLine());
      System.out.println("Enter new Staff ID, new Table ID, new Date "
                         + "(yyyy-mm-dd), and new Time (hh:mm:ss):");
      int newStaffID = Integer.parseInt(scan.nextLine());
      int newTableID = Integer.parseInt(scan.nextLine());
      Date newDate = Date.valueOf(scan.nextLine());
      Time newTime = Time.valueOf(scan.nextLine());
      StaffAssignment assignment =
          new StaffAssignment(newStaffID, newTableID, newDate, newTime);
      repository.update(assignment);
      repository.delete(staffID, tableID, date, time);
      System.out.println("Staff assignment updated successfully.");
    } catch (NumberFormatException e) {
      System.out.println("Invalid input. Please enter valid data.");
      scan.nextLine();
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid input. Please enter valid data.");
      scan.nextLine();
    }
  }

  private static void deleteAssignment() throws SQLException {
    System.out.println("Enter Staff ID, Table ID, Date (yyyy-mm-dd), and "
                       + "Time (hh:mm:ss) to delete:");

    try {
      int staffID = Integer.parseInt(scan.nextLine());
      int tableID = Integer.parseInt(scan.nextLine());
      Date date = Date.valueOf(scan.nextLine());
      Time time = Time.valueOf(scan.nextLine());
      repository.delete(staffID, tableID, date, time);
      System.out.println("Staff assignment deleted successfully.");
    } catch (NumberFormatException e) {
      System.out.println("Invalid input. Please enter valid data.");
      scan.nextLine();
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid input. Please enter valid data.");
      scan.nextLine();
    }
  }

  private static void searchAssignment() throws SQLException {
    System.out.println("Enter Staff ID, Table ID, Date (yyyy-mm-dd), and "
                       + "Time (hh:mm:ss) to search:");

    try {
      int staffID = Integer.parseInt(scan.nextLine());
      int tableID = Integer.parseInt(scan.nextLine());
      Date date = Date.valueOf(scan.nextLine());
      Time time = Time.valueOf(scan.nextLine());
      StaffAssignment assignment =
          repository.findById(staffID, tableID, date, time);
      if (assignment != null) {
        System.out.println(assignment);
      } else {
        System.out.println("Staff assignment not found.");
      }
    } catch (NumberFormatException e) {
      System.out.println("Invalid input. Please enter valid data.");
      scan.nextLine();
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid input. Please enter valid data.");
      scan.nextLine();
    }
  }

  public static void main(String[] args) {
    AssignmentManager manager = new AssignmentManager();
    manager.showMenu();
  }
}
