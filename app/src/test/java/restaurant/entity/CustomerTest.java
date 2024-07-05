package restaurant.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import restaurant.entity.Customer;
import restaurant.entity.superclass.ContactNode;

class CustomerTest {

    @Test
    public void unit_test_initializes_customerID_to_0() {
        Customer customer = new Customer();
        assertEquals(0, customer.getCustomerID());
    }

    @Test
    public void unit_test_handles_null_values_gracefully() {
        Customer customer = new Customer(1, null, null, null, null, null, null);
        assertNull(customer.getFirstName());
        assertNull(customer.getLastName());
        assertNull(customer.getEmail());
        assertNull(customer.getAddress());
        assertNull(customer.getNext());
    }

    @Test
    public void unit_test_initializes_customer_with_valid_inputs() {
        ContactNode nextNode = new ContactNode(
            "Jane",
            "Doe",
            "1234567890",
            "jane.doe@example.com",
            "123 Main St",
            null
        );
        Customer customer = new Customer(
            1,
            "John",
            "Doe",
            "9876543210",
            "john.doe@example.com",
            "456 Elm St",
            nextNode
        );

        assertEquals(1, customer.getCustomerID());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("9876543210", customer.getPhone());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("456 Elm St", customer.getAddress());
        assertEquals(nextNode, customer.getNext());
    }

    @Test
    public void unit_test_initializes_customer_with_customerID_zero() {
        ContactNode nextNode = new ContactNode(
            "Jane",
            "Doe",
            "1234567890",
            "jane.doe@example.com",
            "123 Main St",
            null
        );
        Customer customer = new Customer(
            0,
            "John",
            "Doe",
            "9876543210",
            "john.doe@example.com",
            "456 Elm St",
            nextNode
        );

        assertEquals(0, customer.getCustomerID());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("9876543210", customer.getPhone());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("456 Elm St", customer.getAddress());
        assertEquals(nextNode, customer.getNext());
    }

    @Test
    public void unit_test_returns_correct_customerID_when_valid_customerID_is_set() {
        Customer customer = new Customer(123, "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St", null);
        assertEquals(123, customer.getCustomerID());
    }

    @Test
    public void unit_test_returns_correct_customerID_when_customerID_is_set_to_zero() {
        Customer customer = new Customer(0, "Jane", "Doe", "9876543210", "jane.doe@example.com", "456 Elm St", null);
        assertEquals(0, customer.getCustomerID());
    }

    @Test
    public void unit_test_set_valid_positive_customerID() {
        Customer customer = new Customer();
        int validCustomerID = 123;
        customer.setCustomerID(validCustomerID);
        assertEquals(validCustomerID, customer.getCustomerID());
    }

    @Test
    public void unit_test_set_negative_customerID() {
        Customer customer = new Customer();
        int negativeCustomerID = -123;
        customer.setCustomerID(negativeCustomerID);
        assertEquals(negativeCustomerID, customer.getCustomerID());
    }

    @Test
    public void unit_test_correct_string_format_fully_populated_customer() {
        ContactNode nextNode = new ContactNode(
            "Jane",
            "Doe",
            "1234567890",
            "jane.doe@example.com",
            "123 Main St",
            null
        );
        Customer customer = new Customer(
            1,
            "John",
            "Smith",
            "987654321",
            "john.smith@example.com",
            "456 Elm St",
            nextNode
        );
        String expected =
            "Customer{customerId=1, name='John Smith', firstName='John', " +
            "lastName='Smith', phone=987654321, email='john.smith@example.com', " +
            "address='456 Elm St', next=ContactNode{name='Jane Doe', " +
            "firstName='Jane', lastName='Doe', phone=1234567890, " +
            "email='jane.doe@example.com', address='123 Main St', next=null}}";
        assertEquals(expected, customer.toString());
    }

    @Test
    public void unit_test_extremely_long_strings_in_fields() {
        String longString = "a".repeat(1000);
        ContactNode nextNode = new ContactNode(
            longString,
            longString,
            "1234567890",
            longString + "@example.com",
            longString,
            null
        );
        Customer customer = new Customer(
            1,
            longString,
            longString,
            "987654321",
            longString + "@example.com",
            longString,
            nextNode
        );
        String expected =
            "Customer{customerId=1, name='" +
            longString +
            " " +
            longString +
            "', firstName='" +
            longString +
            "', lastName='" +
            longString +
            "', phone=987654321, email='" +
            longString +
            "@example.com', address='" +
            longString +
            "', next=ContactNode{name='" +
            longString +
            " " +
            longString +
            "', firstName='" +
            longString +
            "', lastName='" +
            longString +
            "', phone=1234567890, email='" +
            longString +
            "@example.com', address='" +
            longString +
            "', next=null}}";
        assertEquals(expected, customer.toString());
    }

    @Test
    public void unit_test_equals_same_customer_object() {
        Customer customer = new Customer(1, "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St", null);
        assertTrue(customer.equals(customer));
    }

    @Test
    public void unit_test_equals_different_customerIDs_identical_ContactNode_attributes() {
        Customer customer1 = new Customer(1, "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St", null);
        Customer customer2 = new Customer(2, "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St", null);
        assertFalse(customer1.equals(customer2));
    }

    @Test
    public void unit_test_hashCode_consistent_values() {
        Customer customer = new Customer(1, "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St", null);
        int initialHashCode = customer.hashCode();
        assertEquals(initialHashCode, customer.hashCode());
        assertEquals(initialHashCode, customer.hashCode());
    }

    @Test
    public void unit_test_hashCode_customerID_zero() {
        Customer customer = new Customer(0, "Jane", "Doe", "987654321", "jane.doe@example.com", "456 Elm St", null);
        int hashCode = customer.hashCode();
        assertNotNull(hashCode);
    }

    @Test
    public void func_test_create_customer_with_valid_details() {
        Customer customer = new Customer(1, "John", "Doe", "1234567890", "john.doe@example.com", "123 Main St", null);
        assertEquals(1, customer.getCustomerID());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("1234567890", customer.getPhone());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("123 Main St", customer.getAddress());
        assertNull(customer.getNext());
    }

    @Test
    public void func_test_create_customer_with_null_or_empty_strings() {
        Customer customer = new Customer(2, "", "", "", "", "", null);
        assertEquals(2, customer.getCustomerID());
        assertEquals("", customer.getFirstName());
        assertEquals("", customer.getLastName());
        assertEquals("", customer.getPhone());
        assertEquals("", customer.getEmail());
        assertEquals("", customer.getAddress());
        assertNull(customer.getNext());
    }
}
