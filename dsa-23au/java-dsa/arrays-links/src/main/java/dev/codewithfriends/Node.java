package dev.codewithfriends;

public class Node<T> {
    public T value;
    public Node<T> next;

//this has the same name as the class
//it is a special method
//it does not have to return anything because
//its return type is the same as the class?
//the object is the return type in the constructor

    public Node(T value) {
        this.value = value;
        this.next = null; // Initialize the next reference to null
    }
}