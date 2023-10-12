//TODO: std library includes

public class HeapWrapper<T implements Comparable> {
    private int size;
    private int maxSize;
    private int[] _array;
    
    public HeapWrapper (int maxSize) {
        this.size = 0;
        this.maxSize = maxSize;
        this._array = new Object[maxSize + 1];
    }

    public void insert(T value) {
        // Make a hole at next empty space
        this.size += 1;
        int i = this.size;
        this._array[i] = value;
        // While the new node violates the heap property with its parent, bubble it up
        while (this._array[i].compareTo(this._array[i/2]) < 0 || i == 1) {
            Object temp = this._array[i/2];
            this._array[i/2] = this._array[i];
            this._array[i] = temp;
        }
    }
    
    public void deleteMin() {
        // Make a hole at the root and save the old min
        Object oldMin = this._array[1];
        // Copy in the last added child
        this._array[1] = this._array[this.size];
        int i = this.size;
        this.size -= 1;
        this._array[i] = value;
        // While the new node violates the heap property with either of its children, bubble it down to that child
        do {
            boolean bubbleLeft = this._array[i].compareTo(this._array[2*i]) > 0;
            boolean bubbleRight = this._array[i].compareTo(this._array[2*i+1]) > 0;

            int bubbleChild = (bubbleLeft) ? 2*i : (bubbleRight) ? 2*i + 1 : i;
            if (bubbleLeft || bubbleRight) {
                continueFlag = true;
                
                Object temp = this._array[bubbleChild];
                this._array[bubbleChild] = this._array[i];
                this._array[i] = temp;
                i = bubbleChild;
            }

            if (i == this.size) {
                continueFlag = false;
            }
        } while (continueChecking);

        return oldMin;
    }
}
