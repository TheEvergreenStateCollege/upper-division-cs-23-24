package com.juniperGMVAD.app.HashMap;

import java.util.ArrayList;
import java.util.List;

public class HashMap<K, V> {
    private final static int CAPACITY_DEFAULT = 64;
    private final static double LOAD_FACTOR_DEFAULT = 0.75;

    private int size = 0;
    private int capacity;
    private double loadFactor;
    private Entry<K, V>[] table;

    private class Entry<K, V> {
        public K key;
        public V value;
        public Entry next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public HashMap() {
        this(CAPACITY_DEFAULT, LOAD_FACTOR_DEFAULT);
    }
    public HashMap(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        table = new Entry[capacity];
    }

    // returns previous value assigned to key
    public V put(K key, V value) {
        if (size >= capacity * loadFactor) {
            resize();
        }

        int index = indexFor(key);

        if (table[index] == null) {
            table[index] = new Entry<K, V>(key, value);
            size++;
            return null;
        }

        Entry prevEntry = null;
        Entry currEntry = table[index];
        while (currEntry != null) {
            if (currEntry.key.equals(key)) {
                V old = (V) currEntry.value;
                currEntry.value = value;
                return old;
            }

            prevEntry = currEntry;
            currEntry = currEntry.next;
        }

        // Entry not found, append to entries linked list
        prevEntry.next = new Entry<K, V>(key, value);
        size++;
        return null;
    }

    public List<V> valuesAsListDebug() {
        List<V> list = new ArrayList<V>();

        for (Entry<K, V> entry : table) {
            Entry currEntry = entry;
            while (currEntry != null) {
                list.add((V) currEntry.value);

                currEntry = currEntry.next;
            }
        }

        return list;
    }

    public V get(K key) {
        int index = indexFor(key);

        Entry currEntry = table[index];
        while (currEntry != null) {
            if (currEntry.key.equals(key)) {
                return (V) currEntry.value;
            }

            currEntry = currEntry.next;
        }

        // Entry not found
        return null;
    }

    public V remove(K key) {
        int index = indexFor(key);
        Entry<K, V> head = table[index];

        if (head == null) {
            return null;
        }

        // Case where head has correct key
        if (head.key.equals(key)) {
            Entry<K, V> next = head.next;
            V prevValue = head.value;

            if (next != null) {
                table[index] = next;
                size--;
                return prevValue;
            }

            table[index] = null;
            size--;
            return prevValue;
        }

        Entry prevEntry = head;
        Entry currEntry = head.next;
        while (currEntry != null) {
            if (currEntry.key.equals(key)) {
                V prevValue = (V) currEntry.value;
                prevEntry.next = currEntry.next;
                size--;
                return prevValue;
            }

            prevEntry = currEntry;
            currEntry = currEntry.next;
        }

        // Entry not found
        return null;
    }

    public List<K> keyList() {
        List<K> result = new ArrayList<K>();

        for (Entry<K, V> e : table) {
            Entry<K, V> currEntry = e;
            while (currEntry != null) {
                result.add(e.key);
                currEntry = currEntry.next;
            }
        }

        return result;
    }

    public int size() {
        return size;
    }

    public int dynamicSizeDebug() {
        int result = 0;
        for (Entry<K, V> e : table) {
            Entry<K, V> currEntry = e;
            while (currEntry != null) {
                result++;
                currEntry = currEntry.next;
            }
        }

        return result;
    }

    public void printHashMapDebug() {
        int index = 0;
        for (Entry<K, V> e : table) {
            System.out.print(index + ": ");

            if (e == null) {
                System.out.print("null");
            }

            Entry<K, V> currEntry = e;
            while (currEntry != null) {
                System.out.print("(" + currEntry.key + ", " + currEntry.value + ")" + "[" + currEntry.key.hashCode() + "]");
                currEntry = currEntry.next;
            }

            System.out.print("\n");
            index++;
        }
    }

    // returns index of key
    private int indexFor(K key) {
        return hash(key, capacity);
    }

    private int hash(K key, int modulo) {
        return key.hashCode() % modulo;
    }

    private void resize() {
        size = 0;
        capacity *= 2;

        Entry<K, V>[] oldTable = table;
        table = new Entry[capacity];

        for (Entry<K, V> e : oldTable) {
            Entry<K, V> currEntry = e;
            while (currEntry != null) {
                put(currEntry.key, currEntry.value);
                currEntry = currEntry.next;
            }
        }
    }
}
