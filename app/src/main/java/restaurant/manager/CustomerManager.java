package restaurant.manager;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import restaurant.entity.Customer;
import restaurant.interfaces.CustomerRepository;
import restaurant.repository.CustomerRepositoryImpl;

public class CustomerManager {

  private static Scanner scan = new Scanner(System.in);
  private static CustomerRepository customerRepository =
      new CustomerRepositoryImpl();

  public void showMenu() {
    String choice = "";

    do {
      System.out.println("Enter your selection\n");
      System.out.println("\ti: insert a new customer\n");
      System.out.println("\tu: update an existing customer\n");
      System.out.println("\td: delete a customer\n");
      System.out.println("\ts: search for a customer\n");
      System.out.println("\tp: print all customers\n");
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
        deleteCustomer();
        break;
      case "s":
        System.out.println(
            "Please enter the customer ID to be searched for:\n");
        int customerID = scan.nextInt();
        scan.nextLine(); // consume newline
        searchCustomer(customerID);
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
    List<Customer> customers = customerRepository.findAll();
    for (Customer customer : customers) {
      System.out.println(customer);
    }
  }

  private static void insert() throws SQLException {
    System.out.println("Enter customer ID, first name, last name, phone "
                       + "number, email, and address:");

    int customerID = scan.nextInt();
    String firstName = scan.next();
    String lastName = scan.next();
    int phoneNumber = scan.nextInt();
    String email = scan.next();
    scan.nextLine();
    String address = scan.nextLine().trim();

    Customer customer = new Customer(customerID, firstName, lastName,
                                     phoneNumber, email, address);
    customerRepository.insert(customer);
  }

  private static void update() throws SQLException {
    System.out.println("Enter the customer ID to update:");
    int customerID = scan.nextInt();
    scan.nextLine(); // consume newline

    System.out.println(
        "Enter new first name, last name, phone number, email, and address:");

    String firstName = scan.next();
    String lastName = scan.next();
    int phoneNumber = scan.nextInt();
    String email = scan.next();
    scan.nextLine();
    String address = scan.nextLine().trim();

    Customer customer = new Customer(customerID, firstName, lastName,
                                     phoneNumber, email, address);
    customerRepository.update(customer);
  }

  private static void searchCustomer(int customerID) throws SQLException {
    Customer customer = customerRepository.findById(customerID);
    if (customer != null) {
      System.out.println(customer);
    } else {
      System.out.println("The customer ID does not exist.");
    }
  }

  private static void deleteCustomer() throws SQLException {
    System.out.println("Enter the customer ID to delete:");
    int customerID = scan.nextInt();
    scan.nextLine(); // consume newline

    customerRepository.delete(customerID);
  }
}
