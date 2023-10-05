package dev.codewithfriends;

public class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null; // Initialize the next reference to null
    }
}