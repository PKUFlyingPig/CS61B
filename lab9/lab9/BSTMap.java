package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.Spring;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author FlyingPig
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (p.key.compareTo(key) == 0) {
            return p.value;
        } else if (p.key.compareTo(key) > 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            return new Node(key, value);
        }
        if (p.key.compareTo(key) == 0) {
            p.value = value;
        } else if (p.key.compareTo(key) > 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.right = putHelper(key, value, p.right);
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
        size++;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    private void traverseAdd(Node p, Set<K> keys) {
        if (p == null) {
            return;
        }
        keys.add(p.key);
        traverseAdd(p.left, keys);
        traverseAdd(p.right, keys);
    }
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        traverseAdd(root, keys);
        return keys;
    }

    /** return a new tree which removes the smallest key
     *  and associated value in the given tree.
     */
    private Node removeMin(K key, Node p) {
        if (p.left == null) {
            return p.right;
        }
        p.left = removeMin(key, p.left);
        return p;
    }

    /** return the node with smallest key in the given tree. */
    private Node min(Node p) {
        if (p.left == null) {
            return p;
        }
        return min(p.left);
    }


    /** return a new tree with the given key removed.
     *  assume that the key is in the tree.
     */
    private Node remove(K key, Node p) {
        int cmp = p.key.compareTo(key);
        if (cmp < 0) {
            p.right = remove(key, p.right);
        } else if (cmp > 0) {
            p.left = remove(key, p.left);
        } else {
            if (p.left == null) return p.right;
            if (p.right == null) return p.left;
            Node t = p;
            p = min(t.right);
            p.right = removeMin(key, t.right);
            p.left = t.left;
        }
        return p;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        V retValue = get(key);
        if (retValue == null) {
            return null;
        }
        root = remove(key, root);
        size--;
        return retValue;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
