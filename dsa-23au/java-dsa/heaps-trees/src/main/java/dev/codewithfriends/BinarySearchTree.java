package dev.codewithfriends;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>{

    private static class BinaryNode<AnyType> {

        // Constructors
        BinaryNode(AnyType theElement)
        {
            this(theElement, null, null); 
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt)

        {
            element = theElement; left = lt; right = rt;

        }

        AnyType element; 
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
    }

    private BinaryNode<AnyType> root;

    public boolean contains(AnyType x)
    {
        return contains(x, root);
    }

    public void insert(AnyType x)
    {
        root = insert(x, root);
    }

    private boolean contains(AnyType x, BinaryNode<AnyType> t)
    // Internal method tofind an item in a subtree.
    // @parm x is item to search for
    // @parm t is node that roots the subtree.
    // @parm return true if the item is found, False otherwise

    {
        if(t == null)
            return false;

        int compareResult = x.compareTo(t.element);
        if(compareResult < 0)
            return contains(x, t.left);
        else if(compareResult > 0)
            return contains(x, t.right);
        else
            return true;
    }

    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t)
    {
        // Internal method to insert into a subtree
        // @parm x the item to insert
        // @parm t the node that roots the subtree.
        // @ return the new root of the subtree.
            if (t == null)
                return new BinaryNode<>(x, null, null);
            
            int compareResult = x.compareTo(t.element);

            if (compareResult < 0)
                t.left = insert(x, t.left);
            else if (compareResult > 0);
                t.right = insert(x, t.right);
            else
                ; 
                return t;
        }

    }

}
