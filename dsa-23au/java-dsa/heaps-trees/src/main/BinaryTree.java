package dev.codewithfriends;

private static class binaryNode<T> 
{
    private T data;
    private binaryNode<T> left;
    private binaryNode<T> right;


    binaryNode(T data)
    {
 
        this.data = data;
        this.left = null;
        this.right = null;

    }

    public T getData ()
    {
        return data;
    }

    public binaryNode<T> getleft() 
    {
    return left;
    }

    public binaryNode<T> getRight() 
    {
        return right;
    }

}
