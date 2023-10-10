// /*
// package dev.codewithfriends;
// import java.io.*;
// import java.util.List;
// import java.util.ArrayList; 
// import java.util.Random;

// public class HashMapperWrapper<K, v> {
// // Objectp[] toArray()
// 	public v _value_array[];
// 	public K _key_array[];
// 	private int a;
// 	private int b;
// 	private int m;

// 	public HashMapperWrapper(int maxSize) {
// 		this._value_array = new v[m]; // instantitae an array 
// 		this.a = Random.nextInt(m);
// 		this.b = Random.nextInt(m);
// 		this.itemCount = 0

// 	public v put(K key, v value);
// 		int hashCode(((K.hashCode * a ) + b % m)) ;
// 		// ?? Set the _value_arry at hashcode tovlaue
// 	}
// 	@param key
// 	@param value
// // 	public static void main(String[] args)
// // 	{
// //  // return Object objects;?
// // 	}
// }
// ===================*******===================
// End of line for free typing in class,
// Below follows the input of the file shared by the professor Paul. 
// Need to dive into it starting soon

// ===================*******===================
//  */

// package dev.codewithfriends;
// import java.util.Random;
// public class HashMapWrapper<K,V> {

//     private V _value_array[];
//     private K _key_array[];
//     private int a;
//     private int b;
//     private int m;
//     private int itemCount;

//     public HashMapWrapper(int m, int maxSize) {
//         this._value_array = new V[m]; // instantiate an array of type V with size maxSize
//         this.a = Random.nextInt(m);
//         this.b = Random.nextInt(m);
//         this.itemCount = 0;
//     }

// /*
//  * Inserts a key-value mapping.
//  * @param key
//  * @param value
//  * @returns
//  */

// public V put(K key, V value) {
//  // check if exceeded max capacity
//     int hashCode = (((K.hashCode * a) + b) % m);
//     ?? // set the _value_array at hashCode to value
//     this.itemCount += 1;
//     return ?? //
// }


// }