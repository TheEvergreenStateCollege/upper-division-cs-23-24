package dev.codewithfriends;

// Readings
// Skiena, "The Algorithm Design Manual" 2nd ed., pp. 116-121
// Weiss, "Data Structures and Algorithm Analysis in Java", 3rd ed.,

public class Heap<T> {

    public static enum HeapType { MIN_HEAP, MAX_HEAP };

    private HeapType type;

    // This heap uses an array because what the hell
    T[] array;

    int size;

    // Location of the hole, when bubbling up or down
    T hole;

    // Get parent index, or -1 if it's already the root
    private int parent(int child) {
        if (child == 0) {
            return -1;
        }
        return (int)child / 2;
    }

    public Heap(Class<T> c, int maxSize, HeapType type) {
        this.type = type;
        this.array = (T[]) java.lang.reflect.Array.newInstance(c, maxSize);
        this.size = 0;
    }

    /**
     *
     * @param number
     * @return true if insertion successful, otherwise false
     */
    public boolean insertHeap(T number) {
        if (this.size > )
    }

}
