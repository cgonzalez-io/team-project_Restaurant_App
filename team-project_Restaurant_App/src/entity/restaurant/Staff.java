package entity.restaurant;

/**
 * The Staff class represents a staff object with their unique ID, name, role, and contact details.
 */
public class Staff {

    private int staffID;
    private String name;
    private String role;
    private String contactDetails;

    // Constructors
    public Staff(int staffID, String name, String role, String contactDetails) {
        this.staffID = staffID;
        this.name = name;
        this.role = role;
        this.contactDetails = contactDetails;
    }

    // Getters and Setters
    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}
