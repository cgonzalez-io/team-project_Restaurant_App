package entity.restaurant;

import java.sql.*;
public class DBManager {

    public static void insertCustomer(Connection connection) {
        try {
            System.out.println("Enter in the Customer ID");
            int customerID = Integer.parseInt(RestaurantApp.scanner.nextLine());
            System.out.print("Enter customer first name: ");
            String firstName = RestaurantApp.scanner.nextLine();
            System.out.print("Enter customer last name: ");
            String lastName = RestaurantApp.scanner.nextLine();
            System.out.print("Enter customer phone number: ");
            String phoneNumber = RestaurantApp.scanner.nextLine();
            System.out.print("Enter customer email: ");
            String email = RestaurantApp.scanner.nextLine();
            System.out.print("Enter customer address: ");
            String address = RestaurantApp.scanner.nextLine();

            try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO CUSTOMER (Customer_ID, First_Name, Last_Name, Phone_Number, Email, Address) " +
                    "VALUES (?, ?, ?, ?, ?, ?)")) {

                statement.setInt(1, customerID);
                statement.setString(2, firstName);
                statement.setString(3, lastName);
                statement.setString(4, phoneNumber);
                statement.setString(5, email);
                statement.setString(6, address);

                int affectedRows = statement.executeUpdate();

                if (affectedRows == 1) {
                    System.out.println("Customer inserted successfully.");
                } else {
                    System.out.println("Failed to insert customer.");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewReservationsForDate(Connection connection) {
        try {
            System.out.print("Enter the date (YYYY-MM-DD): ");
            String date = RestaurantApp.scanner.nextLine();

            try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM RESERVATION WHERE Date = ?")) {

                statement.setString(1, date);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    System.out.println("Reservation ID: " + resultSet.getInt("Reservation_ID"));
                    System.out.println("Customer ID: " + resultSet.getInt("Customer_ID"));
                    System.out.println("Number of People: " + resultSet.getInt("Number_Of_People"));
                    System.out.println("Time: " + resultSet.getTime("Time"));
                    System.out.println("Date: " + resultSet.getDate("Date"));
                    System.out.println("----------------------------");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteReservation(Connection connection) {
        try {
            System.out.print("Enter the date (YYYY-MM-DD): ");
            String date = RestaurantApp.scanner.nextLine();
            System.out.print("Enter the time (HH:MM:SS): ");
            String time = RestaurantApp.scanner.nextLine();

            try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM RESERVATION WHERE Date = ? AND Time = ?")) {

                statement.setString(1, date);
                statement.setString(2, time);
                int affectedRows = statement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Reservation deleted successfully.");
                } else {
                    System.out.println("No reservation found for the specified date and time.");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listStaffAssignments(Connection connection) {
        try {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM assigned_to")) {

                while (resultSet.next()) {
                    System.out.println("Table ID: " + resultSet.getInt("Table_ID"));
                    System.out.println("Staff_ID: " + resultSet.getInt("Staff_ID"));
                    System.out.println("Time: " + resultSet.getTime("Time"));
                    System.out.println("Date: " + resultSet.getDate("Date"));
                    System.out.println("----------------------------");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateReservation(Connection connection) {
        try {
            System.out.print("Enter the reservation ID: ");
            int reservationId = Integer.parseInt(RestaurantApp.scanner.nextLine());
            System.out.print("Enter the new number of people: ");
            int numberOfPeople = Integer.parseInt(RestaurantApp.scanner.nextLine());

            try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE RESERVATION SET Number_Of_People = ? WHERE Reservation_ID = ?")) {

                statement.setInt(1, numberOfPeople);
                statement.setInt(2, reservationId);
                int affectedRows = statement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Reservation updated successfully.");
                } else {
                    System.out.println("No reservation found with the specified ID.");
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
