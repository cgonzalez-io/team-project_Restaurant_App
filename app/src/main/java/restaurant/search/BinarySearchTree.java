package restaurant.search;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
/**
 * A binary search tree based implementation of a symbol table.
 *
 * Completion time: (32hrs)
 *
 * @author Christian
 * @version (202403134v5)
 */

import restaurant.interfaces.BST;

/**
 * BinarySearchTree is an implementation of a binary search tree (BST) that allows for efficient operations such as
 * insertion, deletion, search, and traversal. It implements the BST interface and uses a Node class to represent
 * individual nodes in the tree. The tree is ordered based on the keys of the nodes, with smaller keys on the left
 * and larger keys on the right.
 *
 * @param <Key>   the type of keys in the BST
 * @param <Value> the type of values associated with the keys
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> implements BST<Key, Value> {

    private Node<Key, Value> root;

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    @Override
    public Value get(Key key) {
        Node<Key, Value> iter = root;

        while (iter != null) {
            int cmp = key.compareTo(iter.key);

            if (cmp < 0) iter = iter.left;
            else if (cmp > 0) iter = iter.right;
            else return iter.val;
        }

        return null;
    }

    private Value get(Node<Key, Value> x, Key key) {
        // Return value associated with key in the subtree rooted at x;
        // return null if key not present in subtree rooted at x.
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node<Key, Value> x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;

        return x;
    }

    @Override
    public Key min() {
        if (root == null) throw new NoSuchElementException();
        return min(root).key;
    }

    private Node<Key, Value> min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    @Override
    public Key max() {
        if (root == null) throw new NoSuchElementException();
        return max(root).key;
    }

    private Node<Key, Value> max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    @Override
    public Key floor(Key key) {
        if (root == null) throw new NoSuchElementException();

        Node<Key, Value> x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node<Key, Value> floor(Node<Key, Value> x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node<Key, Value> t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    @Override
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node<Key, Value> select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    @Override
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node<Key, Value> x) {
        // Return number of keys less than x.key in the subtree rooted at x.
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    @Override
    public void deleteMin() {
        if (root == null) throw new NoSuchElementException();
        root = deleteMin(root);
    }

    private Node<Key, Value> deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node<Key, Value> delete(Node<Key, Value> x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Iterable<Key> keys() {
        if (root == null) return new LinkedList<>();
        else return keys(min(), max());
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node<Key, Value> x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to the given key.
     *
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to the given key
     */
    public Key ceiling(Key key) {
        //SKIP, UNNEEDED
        return null;
    }

    /**
     * Returns the root node of the binary search tree.
     *
     * @return the root node of the binary search tree
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Checks if the binary search tree contains a specific key.
     *
     * @param key the key to check
     * @return true if the key is found in the binary search tree, false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * Checks if the binary search tree is empty.
     *
     * @return true if the size of the tree is 0, false otherwise.
     */
    public boolean isEmpty() {
        // if size is 0, then return true else false
        return size() == 0;
    }

    /**
     * Deletes the maximum key in the binary search tree rooted at the given node.
     * Returns the updated root node of the modified tree.
     *
     * @return the updated root node after deletion
     */
    public void deleteMax() {
        // call the recursive method deleteMax
        if (root == null) throw new NoSuchElementException();
        root = deleteMax(root);
    }

    /**
     * Deletes the maximum key in the binary search tree rooted at the given node.
     * Returns the updated root node of the modified tree.
     *
     * @param maxNode the root node of the tree
     * @return the updated root node after deletion
     */
    // recursive deleteMax method implementation
    private Node deleteMax(Node maxNode) {
        if (maxNode.right == null) {
            return maxNode.left;
        }
        maxNode.right = deleteMax(maxNode.right);
        maxNode.N = size(maxNode.left) + size(maxNode.right) + 1;
        return maxNode;
    }

    /**
     * Calculates the number of keys in the symbol table in the given range [lo, hi]. If lo is greater than hi,
     * it returns 0. If hi is in the symbol table, it returns the difference between the rank of hi and the rank of lo plus 1.
     * If hi is not in the symbol table, it returns the difference between the rank of hi and the rank of lo.
     *
     * @param lo the lower bound of the range
     * @param hi the upper bound of the range
     * @return the number of keys in the range [lo, hi]
     */
    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) {
            return 0;
        }
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    /**
     * Inserts a key-value pair into the binary search tree. If the key already exists, updates the value.
     *
     * @param keyToAdd the key to insert
     * @param newValue the value associated with the key
     */
    public void putFast(Key keyToAdd, Value newValue) {
        if (root == null) {
            root = new Node(keyToAdd, newValue, 1);
        } else {
            Node<Key, Value> nod = root;
            Node<Key, Value> parent = null;
            while (nod != null) {
                parent = nod;
                if (keyToAdd.compareTo(nod.key) < 0) {
                    nod = nod.left;
                } else if (keyToAdd.compareTo(nod.key) > 0) {
                    nod = nod.right;
                } else {
                    nod.val = newValue;
                    return;
                }
            }
            nod = root;
            while (nod != null) {
                nod.N = nod.N + 1;
                if (keyToAdd.compareTo(nod.key) < 0) {
                    nod = nod.left;
                } else if (keyToAdd.compareTo(nod.key) > 0) {
                    nod = nod.right;
                }
            }
            Node<Key, Value> newNode = new Node(keyToAdd, newValue, 1);
            if (keyToAdd.compareTo(parent.key) < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
    }

    /**
     * Returns the value paired with a key in the tree. Returns null if key does not exist.
     *
     * @param key the key to find
     * @return the value of the key, or null if key does not exist
     */
    public Value getFast(Key key) {
        Node<Key, Value> n = root;
        while (n != null) {
            int value = key.compareTo(n.key);
            if (value == 0) {
                return n.val;
            } else if (value < 0) {
                n = n.left;
            } else {
                n = n.right;
            }
        }
        return null;
    }

    /**
     * Balances the binary search tree.
     *
     * <p>
     * The balance method performs the following steps to balance the binary search tree:
     * <ol>
     *   <li>Traverses the binary search tree to obtain a linked list of all nodes in the tree.</li>
     *   <li>Rebuilds the binary search tree from the linked list, ensuring that the tree remains balanced.</li>
     * </ol>
     * After the balance method is executed, the root of the binary search tree will be updated to point to the new
     * root of the balanced tree.
     * </p>
     *
     * <p>Usage example:</p>
     * <pre>
     *     BST<String, Integer> bst = new BinarySearchTree<>();
     *     // ... add elements to the bst ...
     *     bst.balance();
     * </pre>
     */
    public void balance() {
        LinkedList<Node> nodes = new LinkedList<>();
        Traversal(root, nodes);
        root = build(nodes, 0, nodes.size() - 1);
    }

    /**
     * Traversal method performs a recursive traversal of a binary search tree starting from a given node.
     * It adds each node to a linked list in the order of the traversal.
     *
     * @param x       the starting node of the traversal
     * @param nodes   the linked list to store the nodes in the traversal order
     */
    private void Traversal(Node<Key, Value> x, LinkedList<Node> nodes) {
        if (x != null) {
            Traversal(x.left, nodes);
            nodes.add(x);
            Traversal(x.right, nodes);
        }
    }

    /**
     * Builds a binary search tree (BST) from a list of nodes.
     *
     * @param nodes the list of nodes to build the BST from
     * @param start the starting index of the sublist to consider
     * @param end the ending index of the sublist to consider
     * @return the root node of the constructed BST
     */
    private Node build(LinkedList<Node> nodes, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end + 1) / 2;
        Node node = nodes.get(mid);
        node.left = build(nodes, start, mid - 1);
        node.right = build(nodes, mid + 1, end);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Returns a string representation of the tree. The ordering is a level traversal of the tree,
     * and each node's value is separated by a space. If there is no valid subtree rooted at the
     * given key, returns "empty".
     *
     * @param key the key of the subtree to display
     * @return the level order string representation of the subtree
     * @throws IllegalArgumentException if the key is null
     */
    public String displayLevel(Key key) {
        if (key == null) throw new IllegalArgumentException();
        if (root == null) return "empty";
        StringBuilder temp = new StringBuilder();
        Queue<Node<Key, Value>> queue = new LinkedList<>();
        Node<Key, Value> currentNode = getNode(key);
        queue.add(currentNode);
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                Node<Key, Value> node = queue.poll();
                if (node == null) {
                    continue;
                }
                temp.append(node.val).append(" ");
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return temp.toString().trim();
    }

    /**
     * Retrieves the node with the specified key in the binary search tree.
     *
     * @param key the key to search for
     * @return the node with the specified key, or null if not found
     */
    private Node<Key, Value> getNode(Key key) {
        return getNode(root, key);
    }

    /**
     * Retrieves the node with the specified key in the binary search tree.
     *
     * @param x the root node of the subtree to search in
     * @param key the key to search for
     * @return the node with the specified key, or null if not found
     */
    private Node<Key, Value> getNode(Node<Key, Value> x, Key key) {
        if (x == null || key == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return getNode(x.left, key);
        else if (cmp > 0) return getNode(x.right, key);
        else return x;
    }

    /**
     * entry point for testing.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BST<Integer, String> bst = new BinarySearchTree();

        bst.put(10, "TEN");
        bst.put(3, "THREE");
        bst.put(1, "ONE");
        bst.put(5, "FIVE");
        bst.put(2, "TWO");
        bst.put(7, "SEVEN");

        System.out.println("Before balance:");
        System.out.println(bst.displayLevel(10)); //root

        System.out.println("After balance:");
        bst.balance();
        System.out.println(bst.displayLevel(5)); //root
    }
}
