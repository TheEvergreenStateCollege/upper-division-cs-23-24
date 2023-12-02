package com.ActivityTor.app;

import java.util.ArrayList;

import javax.swing.SortingFocusTraversalPolicy;

public class BSTNode<T>
{
    T data;
    BSTNode<T> left;
    BSTNode<T> right;
    ArrayList<String> dates = new ArrayList<>();

        //Constructors
    public BSTNode( T data, String date)
    {
        this.data = data;
        this.left = null;
        this.right = null;
        dates.add(date);
    }

    public ArrayList<String> getDate()
    {
        return dates;
    }

    public T getData()
    {
        return data;
    }

    public Object[] findMin() {
        Object[] retArr = new Object[2];
        retArr = this.findMinHelper(retArr);
        return retArr;
    }

    public Object[] findMinHelper(Object[] retArr) {
       
        if(this.left == null){
         retArr[0] = data;
         retArr[1] = dates;
        
         return retArr;
        }
        else {
         this.left.findMinHelper(retArr);
        }
        return retArr;
    }

    public Object[] findMax() {
        Object[] retArr = new Object[2];
        retArr = this.findMaxHelper(retArr);
        return retArr;
    }

    public Object[] findMaxHelper(Object[] retArr) {
        System.out.println(data);
        if(this.right == null){
         retArr[0] = data;
         retArr[1] = dates;
         
         return retArr;
        }
        else {
         this.right.findMaxHelper(retArr);
        }
        return retArr;
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

