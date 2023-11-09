
// Design a dictionary data structure in which search, insertion, and deletion can all be processed in 
// O(1) time in the worst case. You may assume the set elements are integers drawn from a finite set 
// 1,2,..n and initialization can take O(n)

// One way to achieve this is by using an array with a mapping between the integers and their 
// corresponding indices in the array. 

public class ConstantTimeDictionary {
    private int[] array;
    private int[] indexMap;

    public ConstantTimeDictionary(int n) {
        array = new int[n];
        indexMap = new int[n + 1]; // Index 0 is unused, so we add one more element.
        for (int i = 1; i <= n; i++) {
            array[i - 1] = i;
            indexMap[i] = i - 1;
        }
    }

    public void insert(int key, int value) {
        if (key >= 1 && key <= array.length) {
            int index = indexMap[key];
            array[index] = value;
        }
    }

    public int search(int key) {
        if (key >= 1 && key <= array.length) {
            int index = indexMap[key];
            return array[index];
        }
        return -1; // Key is out of range.
    }

    public void delete(int key) {
        if (key >= 1 && key <= array.length) {
            int index = indexMap[key];
            array[index] = key; // Restore the original value
        }
    }

    public static void main(String[] args) {
        int n = 5;
        ConstantTimeDictionary dictionary = new ConstantTimeDictionary(n);

        dictionary.insert(2, 42);
        dictionary.insert(4, 99);

        System.out.println("Dictionary: " + Arrays.toString(dictionary.array));

        int key = 2;
        int value = dictionary.search(key);
        if (value != -1) {
            System.out.println(key + ": " + value);
        } else {
            System.out.println(key + " not found in the dictionary.");
        }

        dictionary.delete(4);
        System.out.println("Dictionary after removing '4': " + Arrays.toString(dictionary.array));
    }
}
