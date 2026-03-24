package design_problems.personal.heap;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap <T extends Comparable<T>> implements Heap<T>{
    private final List<T> heap;

    public MaxHeap() {
        this.heap = new ArrayList<>();
    }

    public void add(T val) {
        heap.add(val);
        upHeap(heap.size() - 1);
    }

    public T peek() {
        if(heap.isEmpty()) {
            throw new RuntimeException("Heap is empty()");
        }

        return heap.getFirst();
    }

    public T poll() {
        if(heap.isEmpty()) {
            throw new RuntimeException("Heap is empty()");
        }

        T max = heap.getFirst();
        heap.set(0, heap.getLast());
        heap.removeLast();

        downHeap(0);

        return max;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void upHeap(int index) {
        if(index == 0) return;
        int parent = parent(index);

        if(heap.get(index).compareTo(heap.get(parent)) > 0) {
            swap(parent, index);
            upHeap(parent);
        }
    }

    private void downHeap(int index) {
        int size = heap.size();

        int left = (2 * index) + 1;
        int right = (2 * index) + 2;

        int largest = index;

        /*
        What Does compareTo() Return?

        compareTo() always returns an int:

        Return Value	    Meaning
        < 0	                This object is smaller
        = 0	                Both are equal
        > 0	                This object is greater

         */

        if(left < size && heap.get(left).compareTo(heap.get(largest)) > 0) {
            largest = left;
        }

        if(right < size && heap.get(right).compareTo(heap.get(largest)) > 0) {
            largest = right;
        }

        if(largest != index) {
            swap(largest, index);
            downHeap(largest);
        }
    }

    private void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }
}