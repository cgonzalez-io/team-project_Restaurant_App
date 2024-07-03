package restaurant.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CounterTest {

  @Test
  public void unit_test_counter_initialized_with_valid_string_id() {
    Counter counter = new Counter("validId");
    assertEquals("validId", counter.toString().split(" ")[1]);
  }
  @Test
  public void unit_test_counter_initialized_with_empty_string_id() {
    Counter counter = new Counter("");
    assertEquals("", counter.toString().split(" ")[1]);
  }
  @Test
  public void unit_test_increment_increases_count_by_1() {
    Counter counter = new Counter("testCounter");
    counter.increment();
    assertEquals(1, counter.tally());
  }
  @Test
  public void unit_test_increment_from_initial_value() {
    Counter counter = new Counter("testCounter");
    assertEquals(0, counter.tally());
    counter.increment();
    assertEquals(1, counter.tally());
  }
  @Test
  public void unit_test_returns_0_when_newly_created() {
    Counter counter = new Counter("testCounter");
    assertEquals(0, counter.tally());
  }
  @Test
  public void unit_test_returns_0_without_any_increments() {
    Counter counter = new Counter("testCounter");
    assertEquals(0, counter.tally());
  }
  @Test
  public void unit_test_returns_correct_string_format_for_default_counter() {
    Counter counter = new Counter("TestCounter");
    String expected = "0 TestCounter";
    assertEquals(expected, counter.toString());
  }
  @Test
  public void unit_test_handles_very_large_count_values_correctly() {
    Counter counter = new Counter("LargeCounter");
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      counter.increment();
    }
    String expected = Integer.MAX_VALUE + " LargeCounter";
    assertEquals(expected, counter.toString());
  }
  @Test
  public void func_test_initialization_with_name_and_zero_count() {
    Counter counter = new Counter("TestCounter");
    assertEquals("TestCounter", counter.toString().split(" ")[1]);
    assertEquals(0, counter.tally());
  }
  @Test
  public void func_test_initialization_with_empty_string_name() {
    Counter counter = new Counter("");
    assertEquals("", counter.toString().split(" ")[1]);
    assertEquals(0, counter.tally());
  }
}
