package entity.restaurant;

/**
 * The Customer class represents a customer object with their unique ID, name, and contact details.
 */
public class Customer {

    private int customerID;
    private String name;
    private String contactDetails;

    // Constructors
    public Customer(int customerID, String name, String contactDetails) {
        this.customerID = customerID;
        this.name = name;
        this.contactDetails = contactDetails;
    }

    // Getters and Setters
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}
