package dev.codewithfriends;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;

public class ArrayWrapper<T> implements List<T> {

    private Object[] _array;
    private int maxSize;
    private int currentSize;
    private String l1;

    public ArrayWrapper(int maxSize) {
        this._array = new Object[maxSize];
        this.currentSize = 0;
    }
    
    
    @Override
    // Returns the current size when called
    public int size() {
        return currentSize;
    }

    @Override
    // Returns boolean if empty
    public boolean isEmpty() {
        if (currentSize == 0) {
            return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[currentSize];

        for (int i = 0; i < currentSize; i++){
            result[i] = _array[i];
        }

        return result;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < currentSize; i++) {
            if (_array[i] == o) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    /*@Override
    public Object[] toArray() {
        List<T> al1= new ArrayList<T>();
        this.add(al1);
        al1.toArray(); 
        return _array;
    }
*/
    /*@Override
    public boolean add(Object o) {
        this._array[currentSize]=o;
        this.currentSize++;
        this.isEmpty = false;
        return true;
    }*/

    @Override
    public boolean add(Object o) {
        // check to ensure array has enough space, resize (2x previous max size) if not
        if (currentSize >= (maxSize / 2)) {
            Object[] resized = new Object[maxSize * 2];
            for (int i = 0; i < currentSize; i++){
                resized[i] = _array[i];
            }

            maxSize = maxSize * 2;
            _array = resized;
        }

        // add element, increment currentSize
        _array[currentSize] = o;
        currentSize++;
        return true;
    }

    //TODO: finish method after implementing remove(int i)
    @Override
    //iterate through the array
    public boolean remove(Object o) {
        int count = 0; // index of object to be removed
        for (int i = 0; i <= _array.length; i++) {
            if (o == _array[i]) {
                count = i;
                break;
            }
        }

        Object result = remove(count);

        if (result != null) {
            return true;
        }

        return false;

        /*//Once found, create an array and add all elements except the removed one
        int newArrSize = maxSize - 1;
        Object[] newArr = new Object[newArrSize];


        for (int i = 0; i <= maxSize; i++) {
            if (array[i] != count) {
                newArr.add(i);
            }
        }

        // if element does not exist, return false
        if (count == null) {
            return false)
        }
        this._array = newArr;*/
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
    public T get(int index) {
        if (index < 0 || index > size()) {
        throw new ArrayIndexOutOfBoundsException();
        }
        return (T)this._array[index];
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    // TODO: check if currentSize is less than half maxSize, resize array
    // Returns null if index is out of bounds of used array indices
    @Override
    public T remove(int index) {
        // check that the index is in range
        // save the Object to be returned
        // loop from end of list to index, shifting items back one slot
        // return the saved Object

        if (index > 0 && index < currentSize) {
            T removed = (T)_array[index];

            for (int i = index + 1; i < currentSize; i++) {
                _array[i - 1] = _array[i];
            }

            return removed;
        }

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
