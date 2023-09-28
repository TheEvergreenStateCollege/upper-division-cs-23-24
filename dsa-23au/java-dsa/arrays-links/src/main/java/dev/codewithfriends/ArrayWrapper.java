package dev.codewithfriends;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayWrapper<T> implements List {

    private Object[] _array;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(<T> o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public <T>[] toArray() {
        return new <T>[0];
    }

    @Override
    public boolean add(<T> o) {
        return false;
    }

    @Override
    public boolean remove(<T> o) {
        int count = null;  //interate through array to find element to remove, assign to count
        for ( i = 0; i <= this._array.length; i += 1) { 
            if (o == this._array[i]) {
                count = i;
                break;
            }
        }
        //create a new array and add all elements except for the one to be removed (count)
        <T> newArr[] = <T> [ArrayWrapper[].size() - 1];
        for (i = 0; i <= newArr[].size(); i += 1) {
            if (i != count) {
            newArr.add(i);
            }
        }
        if (count == null) { //if the element to be removed doesn't exist
        return false;
        }
        this._array = newArr;
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
    public <T> get(int index) {
        return null;
    }

    @Override
    public <T> set(int index, <T> element) {
        return null;
    }

    @Override
    public void add(int index, <T> element) {

    }

    @Override
    public <T> remove(int index) {
        return null;
    }

    @Override
    public int indexOf(<T> o) {
        return 0;
    }

    @Override
    public int lastIndexOf(<T> o) {
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
    public <T>[] toArray(<T>[] a) {
        return new <T>[0];
    }
}
