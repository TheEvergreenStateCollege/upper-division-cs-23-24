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
        return currentSize;
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
    public Object[] toArray() {
        List<T> al1= new ArrayList<T>();
        this.add(al1);
        al1.toArray(); 
        return _array;
    }

    @Override
    public boolean add(Object o) {
        this._array[currentSize]=o;
        this.currentSize++;
        this.isEmpty = false;
        return true;
    }

    @Override
    //iterate throught the array
    public boolean remove(Object o) {
        int count = null; 
        for(i = 0; i<=this._array.length; i+=1){
            if(o == this.array[i]){
                count = i;
                break;  
            }
        }
        //Once found, create an array and add all elements except the removed one
        <T> newArr[] = <T>[ArrayWrapper[].size - 1];
        for(i= 0; i<= newArr[].size(); i+=1) {
            if(i != count){
                newArr.add(i);
            }
        }
        // if element does not exist, return false
        if (count == null){
            return false)
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
        // return the saved Object
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
