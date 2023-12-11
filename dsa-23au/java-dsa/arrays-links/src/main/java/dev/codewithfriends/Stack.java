package dev.codewithfriends;

//2 methods
//push pop :)
//use nodes

public class Stack<T>
{
    public T item;
    private Node<T> top;
    private int size;

    

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T item)
    {
        //Push an element onto the stack
        Node <T> newNode = new Node<> (item);
        newNode.next = top;
        top = newNode;
        size++;
    
    }

    public T pop()
    {
        if(isEmpty())
        {
            throw new IllegalStateException("Stack is empty");
        }
            T item = top.value;
            top = top.next;
            size--;
            return item;
    }

    public T peek(){
        if(isEmpty())
        {
            throw new IllegalStateException("Stack is empty");
        }

        return top.value;
    }

    public int size(){
        return size;
    }
}
