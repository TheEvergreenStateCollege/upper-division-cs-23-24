package com.ndeanon25;

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
