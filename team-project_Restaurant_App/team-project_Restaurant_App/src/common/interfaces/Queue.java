package common.interfaces;

import java.util.NoSuchElementException;

/**
 * A simple queue interface.
 *
 * @version 1.1
 */
public interface Queue<Item> {
    /**
     * Add an item.
     * @param item an item
     */
    public void enqueue(Item item);

    /**
     * Remove the least recently added item.
     * @return an item
     */
    Item dequeue() throws NoSuchElementException;

    /**
     * Return, but do not remove, the least recently added item.
     * @return an item
     */
    Item peek() throws NoSuchElementException;

    /**
     * Is the queue empty?
     * @return if empty
     */
    boolean isEmpty();

    /**
     * Number of items in the queue.
     * @return number of items
     */
    int size();
}
