package dev.codewithfriends;

import java.util.List;
import java.util.Random;

public class HashMapWrapper<K,V> {

    private V _value_array[];
    private K _key_array[];
    private int a;
    private int b;
    private int m;
    private int itemCount;

    public HashMapWrapper(int m, int maxSize) {
        Random rand = new Random();
        this._value_array = (V[]) new Object[m]; // instantiate an array of type V with size maxSize
        long newM = m;
        List<Long> primes;

        // Repeatedly try to find the smallest prime number still greater than m
        do {
            SievePrime sp = new SievePrime((int) newM+10);
            primes = sp.getNumbersLeft();
            newM = primes.get(primes.size()-1);
        } while( newM  <= m);

        this.m = (int)newM; // Somewhere we should check overflow and that we only want primes that fit in an integer
        this.a = rand.nextInt(m); // guaranteed to be co-prime
        this.b = rand.nextInt(m); // guaranteed to be co-prime
        this.itemCount = 0;
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

        int index = (((key.hashCode() * this.a) + this.b) % this.m);
        // check if _value_array index already contains a linked list
        if (_value_array[index] )
        //_value_array[index] = value;
        


        
        // ?? // set the _value_array at index to value
        //this.itemCount += 1;
        //return ?? //
        return null;
    }


}