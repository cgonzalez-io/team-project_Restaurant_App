package common.interfaces;

/**
 * A generic API for a counter which starts at zero and may be increased by one.
 *
 */
public interface IncrementCounter {
      //Increments the counter by one.
      void increment();
      //Returns the increments since creation.
      int tally();
      //Returns a string representation.
      String toString();
}
