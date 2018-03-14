package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * An array based implementation of the Map61B class.
 * @author Josh Hug (mostly done in lecture)
 */
public class ArrayMap<K, V> implements Map61B<K, V> {
    private K[] keys;
    private V[] values;
    int size;

    public ArrayMap() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }

    /** Returns the index of the given key if it exists,
     *  -1 otherwise. */
    private int keyIndex(K key) {
        for (int i = 0; i < size; i += 1) {
            if (keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean containsKey(K key) {
        int index = keyIndex(key);
        return index > -1;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Null key not allowed.");
        }
        if (value == null) {
            throw new IllegalArgumentException("Null values not allowed.");
        }
        int index = keyIndex(key);
        if (index == -1) {
            if (size == keys.length) {
                resize(keys.length * 2);
            }
            keys[size] = key;
            values[size] = value;
            size += 1;
            return;
        }
        values[index] = value;
    }

    private void resize(int capacity) {
        K[] newKeys = (K[]) new Object[capacity];
        V[] newValues = (V[]) new Object[capacity];
        System.arraycopy(keys, 0, newKeys, 0, size);
        System.arraycopy(values, 0, newValues, 0, size);
        keys = newKeys;
        values = newValues;
    }

    @Override
    public V get(K key) {
        int index = keyIndex(key);
        if (index == -1) {
            return null;
        }
        return values[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keyset = new HashSet<>();
        for (int i = 0; i < size; i += 1) {
            keyset.add(keys[i]);
        }
        return keyset;
    }

    @Override
    public V remove(K key) {
        int keyLocation = keyIndex(key);
        V returnValue = null;
        if (keyLocation > -1) {
            returnValue = values[keyLocation];
            keys[keyLocation] = keys[size - 1];
            values[keyLocation] = values[size - 1];
            size -= 1;
        }
        return returnValue;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    @Override
    public void clear() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }

    @Override
    public V remove(K key, V value) {
        int keyLocation = keyIndex(key);
        V returnValue = null;
        if (keyLocation > -1 && values[keyLocation].equals(value)) {
            returnValue = values[keyLocation];
            keys[keyLocation] = keys[size - 1];
            values[keyLocation] = values[size - 1];
            size -= 1;
        }
        return returnValue;
    }
}
