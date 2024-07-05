package restaurant.interfaces;

import restaurant.search.Node;

/**
 * BST defines an interface to a BST implementation of OrderedSymbolTable that
 * offers methods specific to trees.
 *
 * @param <Key>   contained key type
 * @param <Value> contained value type
 * @version 1.0
 */
public interface BST<Key, Value> extends OrderedSymbolTable<Key, Value> {
  /**
   * Puts a key value pair into the tree. If key already exists, then only
   * updates value.
   *
   * @param key key to add
   * @param val value for key
   */
  void putFast(Key key, Value val);

  /**
   * Returns the value paired with a key in the tree. Returns null if key does
   * not exist.
   *
   * @param key key to find
   * @return value of key
   */
  Value getFast(Key key);

  /**
   * Returns a string representation of the tree. The ordering is a level
   * traversal of the tree, and each node's value is separated by a space. If
   * there is no valid subtree rooted at the given key, returns "empty".
   *
   * @return the level order string representation of the tree
   */
  String displayLevel(Key key);

  void balance();

  /**
   * Returns the root node of the BST.
   *
   * @return the root node
   */
  Node<Key, Value> getRoot();
}