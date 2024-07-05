package restaurant.entity.superclass;

import java.util.Objects;

/**
 * A node to hold personal details.
 *
 * @author cjgonz21
 * @version 1.0
 */
public class ContactNode {

  private String name;
  private String firstName;
  private String lastName;
  private String phone;
  private String email;
  private String address;
  private ContactNode next;

  public ContactNode() {
    name = "";
    phone = "";
    email = "";
    next = null;
  }

  public ContactNode(String firstName, String lastName, String phone,
                     String email, String address, ContactNode next) {
    this.name = firstName + " " + lastName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.email = email;
    this.address = address;
    this.next = next;
  }

  @Override
  public String toString() {
    return ("ContactNode{"
            + "name='" + name + '\'' + ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' + ", phone=" + phone +
            ", email='" + email + '\'' + ", address='" + address + '\'' +
            ", next=" + next + '}');
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof ContactNode that))
      return false;
    return (phone == that.phone && Objects.equals(name, that.name) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(email, that.email) &&
            Objects.equals(address, that.address) &&
            Objects.equals(next, that.next));
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, firstName, lastName, phone, email, address, next);
  }

  public String getFirstName() { return firstName; }

  public void setFirstName(String firstName) { this.firstName = firstName; }

  public String getLastName() { return lastName; }

  public void setLastName(String lastName) { this.lastName = lastName; }

  public String getAddress() { return address; }

  public void setAddress(String address) { this.address = address; }

  public String getName() { return name; }

  public void setName(String n) { name = n; }

  public String getPhone() { return phone; }

  public void setPhone(String p) { phone = p; }

  public String getEmail() { return email; }

  public void setEmail(String e) { email = e; }

  public ContactNode getNext() { return next; }

  public void setNext(ContactNode n) { next = n; }
}
