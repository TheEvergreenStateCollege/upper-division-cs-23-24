package dev.codewithfriends;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;

public class ArrayWrapper<T> implements List {
    private Object[] _array;
    private int maxSize;
    private int currentSize;
    private boolean isEmpty;
    private String l1;

    public ArrayWrapper(int maxSize) {
        this._array = new Object[maxSize];
        this.currentSize = 0;
        this.isEmpty = true;
    }
    
    
    @Override
    // Returns the current size when called
    public int size() {
        return maxSize;
    }

    @Override
    // Returns boolean if empty
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public boolean add(Object o) {
        this._array[currentSize]=o;
        this.currentSize++;
        this.isEmpty = false;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        // check that the index is in range
        // save the Object to be returned
        // loop from end of list to index, shifting items back one slot
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        List<T> al1= new ArrayList<T>();
        this.add(al1);
        Object object = al1.toArray(); 
        return _array;
    // }
    }
}
