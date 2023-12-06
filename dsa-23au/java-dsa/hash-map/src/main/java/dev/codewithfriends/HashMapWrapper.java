package dev.codewithfriends;

import java.util.*;
import java.util.stream.Collectors;

public class HashMapWrapper<K,V> implements Map<K,V> {
    private List<Map.Entry<K,V>> _value_array[];
    
    private final int a;
    private final int b;
    private final int m;
    private int itemCount;
    private int currentSize;
    private int maxSize;

    public class EntryWrapper implements Map.Entry<K, V> {

        private K _key;
        private V _value;

        public EntryWrapper(K key, V value) {
            this._key = key;
            this._value = value;
        }

        @Override
        public K getKey() {
            return _key;
        }

        @Override
        public V getValue() {
            return _value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this._value;
            this._value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            try {
                Map.Entry<K,V> other = (Map.Entry<K,V>)o;
                return (this._key.equals(other.getKey()) && (this._value.equals(other.getValue())));
            } catch(ClassCastException cce) {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return hash(_key);
        }
    }

    public HashMapWrapper(int m, int maxSize) {
        Random rand = new Random();
        this._value_array = (List<Map.Entry<K,V>>[]) new Object[m]; // instantiate an array of type V with size maxSize
        long newM = m;
        List<Long> primes;

        int jump = 10;
        // Repeatedly try to find the smallest prime number still greater than m
        do {
            SievePrime sp = new SievePrime((int) newM+jump);
            primes = sp.getNumbersLeft();
            newM = primes.get(primes.size()-1);
            jump *= 2; // double the search space if we don't find a prime
        } while( newM  <= m); // we'll only loop if

        this.m = (int)newM; // Somewhere we should check overflow and that we only want primes that fit in an integer
        this.a = rand.nextInt(m); // guaranteed to be co-prime
        this.b = rand.nextInt(m); // guaranteed to be co-prime
        this.itemCount = 0;
    }

    public int hash(K key) {
        return (((key.hashCode() * this.a) + this.b) % this.m);
    }

    @Override
    public V get(Object key) {
        try {
            int index = hash((K)key);

            if (_value_array[index] != null) {
                List<Map.Entry<K,V>> matches =_value_array[index].stream().filter((Map.Entry<K,V> e) -> e.getKey().equals(key)).collect(Collectors.toList());
                if (matches.size() > 0) {
                    return matches.get(0).getValue();
                }
            }
            return null;    
        } catch (ClassCastException cce) {
            return null;
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    /**
 * Inserts a key-value mapping.
 * @param key
 * @param value
 * @returns
 */
    public V put(K key, V value) {
        // check if exceeded max capacity
        if (this.currentSize >= this.maxSize) {
            return null;
        }

        int index = hash(key);

        Map.Entry<K,V> testObj = new EntryWrapper(key, value);
        int match =_value_array[index].indexOf(testObj);
        if (match != -1) {
            return _value_array[index].get(match).getValue();
        }

        // check if _value_array index already contains a linked list
        if (_value_array[index] != null) {
            _value_array[index].add(new EntryWrapper(key, value));
        } else {
            List<Map.Entry<K,V>> collisions = new LinkedList<>();
            collisions.add(new EntryWrapper(key, value));
            _value_array[index] = collisions;
        }
        this.itemCount += 1;
        return value;
    }

    @Override
    public V remove(Object key) {
        throw new RuntimeException("Not yet implemented.");
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.entrySet().parallelStream().forEach((entry) -> put(entry.getKey(), entry.getValue()));
    }

    @Override
    public void clear() {
        throw new RuntimeException("Not yet implemented.");
    }

    @Override
    public Set<K> keySet() {
        throw new RuntimeException("Not yet implemented.");
    }

    @Override
    public Collection<V> values() {
        throw new RuntimeException("Not yet implemented.");
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new RuntimeException("Not yet implemented.");
    }

    @Override
    public boolean equals(Object o) {
        throw new RuntimeException("Not yet implemented.");
    }

    @Override
    public int hashCode() {
        throw new RuntimeException("Not yet implemented.");
    }


}