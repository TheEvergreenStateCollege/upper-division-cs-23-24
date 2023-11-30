package com.ActivityTor.app;


    public class BSTNode<T>
{
    T data;
    BSTNode<T> left;
    BSTNode<T> right;
    String date;

        //Constructors
    public BSTNode( T data, String date)
    {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public String getDate()
    {
        return date;
    }

    public T getData()
    {
        return data;
    }

    public BSTNode<T> getLeft()
    {
        return right;
    }


    public BSTNode<T> getRight()
    {
        return left;
    }
}

