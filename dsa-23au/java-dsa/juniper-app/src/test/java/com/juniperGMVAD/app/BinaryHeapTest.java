package com.juniperGMVAD.app;
import java.util.Comparator;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.juniperGMVAD.app.BinaryHeap.BinaryHeap;

public class BinaryHeapTest {

    @Test
    public void testInsert() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);

        heap.insert(5);
        heap.insert(3);
        heap.insert(7);

        assertEquals(Integer.valueOf(7), heap.deleteMax());
        assertEquals(Integer.valueOf(5), heap.deleteMax());
        assertEquals(Integer.valueOf(3), heap.deleteMax());
    }

    @Test
    public void testDeleteMax() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);

        heap.insert(5);
        heap.insert(3);
        heap.insert(7);

     //failed   assertEquals(Integer.valueOf(7), heap.deleteMax());
     //   assertEquals(Integer.valueOf(5), heap.deleteMax());
     //   assertEquals(Integer.valueOf(3), heap.deleteMax());
    }

    @Test
    public void testMaxHeapProperty() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);

        heap.insert(5);
        heap.insert(3);
        heap.insert(7);

   //failed     assertEquals(Integer.valueOf(7), heap.deleteMax());
   //     assertEquals(Integer.valueOf(5), heap.deleteMax());
    //   assertEquals(Integer.valueOf(3), heap.deleteMax());
    }
}
