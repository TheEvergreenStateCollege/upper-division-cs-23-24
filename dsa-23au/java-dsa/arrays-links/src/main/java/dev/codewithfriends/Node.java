package dev.codewithfriends;

public class Node<T> {
    public T value;
    public Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null; // Initialize the next reference to null
    }
}