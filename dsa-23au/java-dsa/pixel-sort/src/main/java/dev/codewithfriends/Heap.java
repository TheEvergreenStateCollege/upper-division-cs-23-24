package dev.codewithfriends;

public class Heap {

    public static enum HeapType { MIN_HEAP, MAX_HEAP };

    private HeapType type;

    // This heap uses an array because what the hell
    int[] array;

    int size;

    // Location of the hole
    int hole;

    public Heap(int maxSize, HeapType type) {
        this.array = new int[maxSize];
        this.type = type;
    }

    public int deleteMin() {
        if (size > 0) {
            
        }
    }

    public void propagateUp()
}
