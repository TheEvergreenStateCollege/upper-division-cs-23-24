
package dev.codewithfriends;

private static class BinaryNode<T>
{
    private T data;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

        //Constructors
    BinaryNode( T, data )
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

