package dev.codewithfriends;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayWrapper<T> implements List {

    private Object[] _array;
    private int maxSize;
    private int currentSize;
    private boolean isEmpty;

    public ArrayWrapper(int maxSize) {
        this._array = new Object[maxSize];
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.isEmpty = true;
    }
    
    
    @Override
    public int size() {
        return this.maxSize;
    }

    @Override
    public boolean isEmpty() {
        return this.isEmpty;
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
    public Object[] toArray() {
        return new Object[0];
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
        return this._array[index];
    }

    @Override
    public Object set(int index, Object element) {
        if (index > maxSize) {
            return null;
        }
        else {
            Object previousElement = this._array[index];
            this._array[index] = element;
            return previousElement;
        }
    }

    @Override
    public void add(int index, Object element) {
        if (this.currentSize != this.maxSize) {
            for (int i=0; i < this.currentSize-(index);i++){
                _array[this.currentSize-i] = _array[this.currentSize-i-1];
            }
            _array[index] = element;
            this.currentSize++;
            this.isEmpty = false;
        }
    }

    @Override
    public Object remove(int index) {
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
        return new Object[0];
    }
}
