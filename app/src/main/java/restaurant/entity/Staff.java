package restaurant.entity;

import java.util.Objects;
import restaurant.entity.superclass.ContactNode;

/**
 * The Staff class represents a staff member in a restaurant.
 * It contains information such as staff ID, first name, last name,
 * contact number, email address, address, and role.
 */
public class Staff extends ContactNode {

  private int staffID;
  private String role;

  public Staff() {
    super();
    this.staffID = 0;
    this.role = "";
  }

  public Staff(int staffId, String firstName, String lastName, long phone,
               String email, String address, String role, ContactNode next) {
    super(firstName, lastName, phone, email, address, next);
    this.staffID = staffId;
    this.role = role;
  }

  public int getStaffId() { return staffID; }

  public void setStaffId(int staffId) { this.staffID = staffId; }

  public String getRole() { return role; }

  public void setRole(String role) { this.role = role; }

  @Override
  public String toString() {
    return ("Staff{"
            + "staffId=" + staffID + ", role='" + role + '\'' + ", name='" +
            getName() + '\'' + ", firstName='" + getFirstName() + '\'' +
            ", lastName='" + getLastName() + '\'' + ", phone=" + getPhone() +
            ", email='" + getEmail() + '\'' + ", address='" + getAddress() +
            '\'' + ", next=" + getNext() + '}');
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Staff that))
      return false;
    if (!super.equals(o))
      return false;
    return staffID == that.staffID && Objects.equals(role, that.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), staffID, role);
  }
}
