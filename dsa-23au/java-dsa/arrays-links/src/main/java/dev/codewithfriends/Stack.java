package dev.codewithfriends;
import 

//2 methods
//push pop :)
//use nodes

public class Stack<T>
{
    private T item;
    private Node<T> top;

    public void push(T item)
    {

        Node <T> newNode = new Node<> (item);
        newNode.next = top;
        top = newNode;
    
    }

    public <T> pop(T item)
    {

        if(isEmpty()) 
        {
            return null;
        }
            T item = top.item;
            top = top.next;
            return item;
    }
}
