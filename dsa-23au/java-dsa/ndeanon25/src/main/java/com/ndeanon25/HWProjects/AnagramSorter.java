package com.ndeanon25.HWProjects;

import java.util.Arrays;
import java.util.Comparator;

public class AnagramSorter {
    //Group Anagrams: Write a method to sort an array of strings so that all the anagrams are next to each other.

        private static class AnagramComparator implements Comparator<String>{
            private String sortString(String str){
                char[] charArray = str.toCharArray();
                Arrays.sort(charArray);
                return new String(charArray);
            }
            
            public int compare(String str1, String str2){
                String sortedString1 = sortString(str1);
                String sortedString2 = sortString(str2);
                return sortedString1.compareTo(sortedString2);
            }
        }
        
        public static void sortStringsByAnagrams(String[] arrStrings){
            Arrays.sort(arrStrings, new AnagramComparator());
        }


    public static void main(String[] args){
        String[] strArr = {"car", "wolf", "dog","hat", "flow"};
        System.out.println("Original Array: ");
            for(String str:strArr){
            System.out.println(str + " ");  
            } 
        sortStringsByAnagrams(strArr);

        System.out.println( "\nSorted Array with anagrams next to each other.");
            for(String str:strArr){
                System.out.println(str + " ");
            }
    }

   

    
}

/** Problem 10.10
 *  Rank from Stream: Imagine you are reading in a stream of integers. Periodically, you wish
 * to be able to look up the rank of a number x (the number of values less than or equal to x).
 * Implement the data structures and algorithms to support these operations. That is, implement
 * the method track ( int x), which is called when each number is generated, and the method
 * getRankOfNumber( int x), which returns the number of values less than or equal to x (not
 * including x itself).
 * 
 * Method track(x):
        # Inserts x into the binary search tree and updates the count property
        root = insert(root, x)

    Method insert(node, x):
        if node is null:
            Create a new node with value x and count = 1
            Return the new node

        if x < node.value:
            node.left = insert(node.left, x)
        else if x > node.value:
            node.right = insert(node.right, x)
        else:
            # If x is equal to node.value, increment node.count

        # Update the count property of the current node
        node.count = 1 + getCount(node.left) + getCount(node.right)

        Return the modified node

        
 * Method getRankOfNumber(x):
        # Returns the number of values less than or equal to x
        return getRank(root, x)

    Method getRank(node, x):
        if node is null:
            return 0

        if x < node.value:
            return getRank(node.left, x)
        else if x > node.value:
            return 1 + getCount(node.left) + getRank(node.right, x)
        else:
            # If x is equal to node.value, return the rank
            return getCount(node.left)

 
 */
