package restaurant.entity;

import java.util.Objects;
import restaurant.entity.superclass.ContactNode;

/**
 * The Customer class represents a customer in a system.
 * It contains information such as customer ID, first name, last name,
 * contact number, email address, and address.
 */
public class Customer extends ContactNode {

  private int customerID; // primary key

  public Customer() {
    super();
    customerID = 0;
  }
  // Add this constructor to fix the issue
  public Customer(int customerID, String firstName, String lastName, String phone,
                  String email, String address) {
    super(firstName, lastName, phone, email, address, null);
    this.customerID = customerID;
  }

  public Customer(int customerID, String firstName, String lastName, String phone,
                  String email, String address, ContactNode next) {
    super(firstName, lastName, phone, email, address, next);
    this.customerID = customerID;
  }

  public int getCustomerID() { return customerID; }

  public void setCustomerID(int customerID) { this.customerID = customerID; }

  @Override
  public String toString() {
    return ("Customer{"
            + "customerId=" + customerID + ", name='" + getName() + '\'' +
            ", firstName='" + getFirstName() + '\'' + ", lastName='" +
            getLastName() + '\'' + ", phone=" + getPhone() + ", email='" +
            getEmail() + '\'' + ", address='" + getAddress() + '\'' +
            ", next=" + getNext() + '}');
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Customer that))
      return false;
    if (!super.equals(o))
      return false;
    return customerID == that.customerID;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), customerID);
  }
}
