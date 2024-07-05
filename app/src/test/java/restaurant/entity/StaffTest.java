package restaurant.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StaffTest {

  @Test
  public void testInitialStaff() {
    Staff staff = new Staff(1, null, null, null, null, null, null);
    assertEquals(1, staff.getStaffId());
    assertNull(staff.getFirstName());
    assertNull(staff.getLastName());
    assertNull(staff.getPhone());
    assertNull(staff.getAddress());
    assertNull(staff.getEmail());
    assertNull(staff.getRole());
  }

  @Test
  public void testStaffValues() {
    Staff staff = new Staff(1, "John", "Doe", "1234567890", "john@email.com",
                            "123 Main St", "Server");
    assertEquals(1, staff.getStaffId());
    assertEquals("John", staff.getFirstName());
    assertEquals("Doe", staff.getLastName());
    assertEquals("1234567890", staff.getPhone());
    assertEquals("123 Main St", staff.getAddress());
    assertEquals("john@email.com", staff.getEmail());
    assertEquals("Server", staff.getRole());
  }

  @Test
  public void testSettersAndGetters() {
    Staff staff = new Staff();
    staff.setStaffId(2);
    staff.setFirstName("John");
    staff.setLastName("Doe");
    staff.setPhone("1234567890");
    staff.setAddress("123 Main St");
    staff.setEmail("john@email.com");
    staff.setRole("Greeter");
    assertEquals(2, staff.getStaffId());
    assertEquals("John", staff.getFirstName());
    assertEquals("Doe", staff.getLastName());
    assertEquals("1234567890", staff.getPhone());
    assertEquals("123 Main St", staff.getAddress());
    assertEquals("john@email.com", staff.getEmail());
    assertEquals("Greeter", staff.getRole());
  }

  @Test
  public void testNegativeStaffId() {
    Staff staff = new Staff();
    staff.setStaffId(-1);
    assertEquals(-1, staff.getStaffId());
  }

  @Test
  public void testNullRole() {
    Staff staff = new Staff();
    staff.setRole(null);
    assertNull(staff.getRole());
  }

  @Test
  public void testEqualsNull() {
    Staff staff = new Staff(1, "John", "Doe", "1234567890", "john@email.com",
                            "123 Main St", "Chef");
    assertNotEquals(staff, null);
  }

  @Test
  public void testEqualsDifferentStaffId() {
    Staff staff1 = new Staff(1, "John", "Doe", "1234567890", "john@email.com",
                             "123 Main St", "Chef");
    Staff staff2 = new Staff(2, "John", "Doe", "1234567890", "john@email.com",
                             "123 Main St", "Chef");
    assertNotEquals(staff1, staff2);
  }

  @Test
  public void testEqualsDifferentRole() {
    Staff staff1 = new Staff(1, "John", "Doe", "1234567890", "john@email.com",
                             "123 Main St", "Chef");
    Staff staff2 = new Staff(1, "John", "Doe", "1234567890", "john@email.com",
                             "123 Main St", "Manager");
    assertNotEquals(staff1, staff2);
  }
}
