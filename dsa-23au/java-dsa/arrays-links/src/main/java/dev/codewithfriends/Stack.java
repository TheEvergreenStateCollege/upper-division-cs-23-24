package dev.codewithfriends;

//2 methods
//push pop :)
//use nodes

public class Stack<T>
{
    private T item;
    private Node<T> top;

    public boolean isEmpty() {
        throw new RuntimeException("Not yet implemented.");
    }

    public void push(T item)
    {

        Node <T> newNode = new Node<> (item);
        newNode.next = top;
        top = newNode;
    
    }

    public T pop()
    {
        if(isEmpty())
        {
            return null;
        }
            T item = top.value;
            top = top.next;
            return item;
    }
}
