pakcage dev.codewithfriends;

public class HashMapWrapper<K,V> {

    private V _array[];

private v _value_array[];
        private K _value_array[];
        private int a;
        private int b;

    public HashMapWrapper(int m, int maxSize) {
        this._value_array = ?? //instantiate an array of 
        this.a = Random.nextInt(m);
        this.b = Random.nextInt(m);


    }

    public V put(K key, V value) {
        int hashCode = (((k.hashCode * a) + b) % m);
        ?? // set the _value_array at hashCode to value
    }
}