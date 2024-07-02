package entity.restaurant;

/**
 * The Customer class represents a customer object with their unique ID, name, and contact details.
 */
public class Customer {

    private int customerID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String address;


    // Constructors
    public Customer(int customerID, String firstName, String lastName, String phoneNumber, String email, String address) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    // Getters and Setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "customerID=" + customerID +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", email='" + email + '\'' +
            ", address='" + address + '\'' +
            '}';
    }
}
