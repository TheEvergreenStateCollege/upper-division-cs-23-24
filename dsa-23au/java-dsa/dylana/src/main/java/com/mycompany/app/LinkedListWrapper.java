package dev.codewithfriends;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedListWrapper<T> implements List<T> {

    private int maxSize;
    private int currentSize;
    private Node<T> head;
    private Node<T> tail;
    

    public LinkedListWrapper(int maxSize) {
        this.maxSize = maxSize;
        this.head = null;
    }

    @Override
    public int size() {
        return this.currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

       @Override
    public boolean contains(Object o) {
        Node current = head; 
        //requieres the size of the LinkedListWrapper to be something any method can access in the class
        while (current != null) {
            if (current.value.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;

    }
    @Override
    ///Skipped during class 10/12/2023
    public Iterator iterator() {
        return null;
        
    }

    @Override
    public Object[] toArray() {
        Object[] returnArray = new Object[this.currentSize];
        int i = 0;
        Node<T> current = head;
        while (current != null) {
            returnArray[i] = current.value;
            i++;
           current = current.next;
        }
        return returnArray;
    }

    @Override
    public boolean add(T o) {
        Node<T> newNode = new Node<T>(o); // Create a new node with the object o as payload

        if (currentSize >= maxSize) {
            return false;
        }

        if (this.head == null) {
            this.head = newNode; // If the list is empty, set the new node as the head
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }

        currentSize++;
        return true; // Indicate that the addition was successful
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
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, Object element) {
        return null;
    }

    private Node<T> addBefore(Node<T> target, Node<T> newNode) {
        newNode.next = target;
        return newNode;
    }

    @Override
    public void add(int index, T element) {
        if (index >= this.currentSize) {
            return;
        }
        int i = 0;
        Node<T> newNode = new Node<T>(element);
        Node<T> current = this.head;
        Node<T> prev = this.head;
        while (i < index && this.head != null) {
            prev = current;
            current = current.next;
            i += 1;
        }
        Node<T> beforeNode = addBefore(current, newNode);
        if (prev == null || prev == this.head) {
            this.head = newNode;
        } else {
            prev.next = newNode;
        }
    }

    @Override
    public T remove(int index) {
        //Return 0 if the list has nothing in it
        if(index < 0 || index >= this.currentSize){
            throw new IndexOutOfBoundsException("Index out of bounds");
        } 
        //if the list has only one element in it
        if(index == 0){
            head = head.next; //removes the head
            
        } else {
            Node<T> temp = head;
            for(int i = 0; i < index-1;i++){
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }
        this.currentSize--;
        return null;
    }

    @Override
    public int indexOf(Object o) {
        Node curr = this.head;
        int temp = 0;
        while(curr!=null){
            if(curr.value.equals(o)){
            return temp;
            }
            else{
                temp += 1;
            }

        }

        return -1;
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
        throw new RuntimeException("Not yet implemented.");
    }
    
}


