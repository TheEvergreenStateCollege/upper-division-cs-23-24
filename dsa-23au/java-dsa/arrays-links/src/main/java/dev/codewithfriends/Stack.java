/*package dev.codewithfriends

//2 methods
//push pop :)
//use nodes

public class stack<T>
{
    private T item;
    private Node<T> top;

   public void push(T item)
   {

    private Node <T> newNode = new Node<> (item);
    newNode.Next = top;
    top = newNode;
    
   }

   public <T> pop(T item)
   {

    if(isEmpty()) 
    {
        return null;
        T item = top.item;
        top = top.Next;
        return item;
    }


   }


}
*/