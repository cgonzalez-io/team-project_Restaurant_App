package restaurant.manager;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import restaurant.entity.Staff;
import restaurant.interfaces.StaffRepository;
import restaurant.repository.StaffRepositoryImpl;

public class StaffManager {

  private static Scanner scan = new Scanner(System.in);
  private static StaffRepository staffRepository = new StaffRepositoryImpl();

  public static void showMenu(String[] args) {
    String choice = "";

    do {
      System.out.println("Enter your selection\n");
      System.out.println("\ti: insert a new staff member\n");
      System.out.println("\tu: update an existing staff member\n");
      System.out.println("\td: delete a staff member\n");
      System.out.println("\ts: search for a staff member\n");
      System.out.println("\tp: print all staff members\n");
      System.out.println("\tq: quit \n");
      choice = scan.nextLine();
      branching(choice);
    } while (!choice.toLowerCase().equals("q"));
  }

  private static void branching(String choice) {
    try {
      switch (choice) {
      case "i":
        insertStaff();
        break;
      case "u":
        updateStaff();
        break;
      case "d":
        deleteStaff();
        break;
      case "s":
        System.out.println("Please enter the staff ID to be searched for:\n");
        int staffID = scan.nextInt();
        scan.nextLine(); // consume newline
        searchStaff(staffID);
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
    List<Staff> staffList = staffRepository.findAll();
    for (Staff staff : staffList) {
      System.out.println(staff);
    }
  }

  private static void insertStaff() throws SQLException {
    System.out.println("Enter staff ID, first name, last name, phone, email, "
                       + "address, and role:");

    String fName = scan.nextLine();
    String lName = scan.nextLine();
    String phone = scan.nextLine();
    String email = scan.nextLine();
    String address = scan.nextLine();
    String role = scan.nextLine();
    scan.nextLine(); // consume newline

    Staff staff = new Staff(fName, lName, phone, email, address, role);
    staffRepository.insert(staff);
  }

  private static void updateStaff() throws SQLException {
    System.out.println("Enter the staff ID to update:");
    int staffID = scan.nextInt();
    scan.nextLine(); // consume newline

    System.out.println(
        "Enter new first name, last name, phone, email, address, and role:");

    String fName = scan.nextLine();
    String lName = scan.nextLine();
    String phone = scan.nextLine();
    String email = scan.nextLine();
    String address = scan.nextLine();
    String role = scan.nextLine();
    scan.nextLine(); // consume newline

    Staff staff = new Staff(staffID, fName, lName, phone, email, address, role);
    staffRepository.update(staff);
  }

  private static void searchStaff(int staffID) throws SQLException {
    Staff staff = staffRepository.findById(staffID);
    if (staff != null) {
      System.out.println(staff);
    } else {
      System.out.println("The staff ID does not exist.");
    }
  }

  private static void deleteStaff() throws SQLException {
    System.out.println("Enter the staff ID to delete:");
    int staffID = scan.nextInt();
    scan.nextLine(); // consume newline

    staffRepository.delete(staffID);
  }
}
