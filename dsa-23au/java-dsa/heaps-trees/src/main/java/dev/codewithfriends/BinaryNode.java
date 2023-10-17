package dev.codewithfriends;

public class BinaryNode<T>
{
    T data;
    BinaryNode<T> left;
    BinaryNode<T> right;

        //Constructors
    public BinaryNode( T data )
    {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public T getData()
    {
        return data;
    }

    public BinaryNode<T> getLeft()
    {
        return right;
    }


    public BinaryNode<T> getRight()
    {
        return left;
    }
}