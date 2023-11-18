package dev.codewithfriends;

import java.util.*;

public class ArrayWrapper<T> implements List<T> {

    private T[] _array;
    private int maxSize;
    private int currentSize;

    public ArrayWrapper(int maxSize) {
        if (maxSize < 0) {
            throw new IllegalArgumentException("Max size must be non-negative");
        }
        this.maxSize = maxSize;
        this._array = (T[]) new Object[maxSize];
        this.currentSize = 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.asList(_array).subList(0, currentSize).iterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(_array, currentSize);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < currentSize) {
            return (T1[]) Arrays.copyOf(_array, currentSize, a.getClass());
        }
        System.arraycopy(_array, 0, a, 0, currentSize);
        if (a.length > currentSize) {
            a[currentSize] = null;
        }
        return a;
    }

    @Override
    public boolean add(T e) {
        ensureCapacity();
        _array[currentSize++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T element : c) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        // This method requires a more complex implementation
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        Arrays.fill(_array, 0, currentSize, null);
        currentSize = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return _array[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T oldValue = _array[index];
        _array[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        checkIndexForAdd(index);
        ensureCapacity();
        System.arraycopy(_array, index, _array, index + 1, currentSize - index);
        _array[index] = element;
        currentSize++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = _array[index];
        int numMoved = currentSize - index - 1;
        if (numMoved > 0) {
            System.arraycopy(_array, index + 1, _array, index, numMoved);
        }
        _array[--currentSize] = null; // Clear to let GC do its work
        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < currentSize; i++) {
            if (o == null ? _array[i] == null : o.equals(_array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        // This method requires iterating from the end of the list
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListIterator<T> listIterator() {
        return Arrays.asList(_array).subList(0, currentSize).listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return Arrays.asList(_array).subList(0, currentSize).listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        // This method should return a view of the portion of this list
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // This method requires a more complex implementation
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // This method requires a more complex implementation
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    private void ensureCapacity() {
        if (currentSize == maxSize) {
            maxSize *= 2;
            _array = Arrays.copyOf(_array, maxSize);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + currentSize);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > currentSize) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + currentSize);
        }
    }
}
