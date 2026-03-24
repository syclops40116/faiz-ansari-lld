package design_problems.personal.heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> implements Heap<T> {
    private final List<T> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    @Override
    public void add(T val) {
        heap.add(val);
        int index = heap.size() - 1;
        upHeap(index);
    }

    @Override
    public T peek() {
        if(heap.isEmpty()) {
            throw new RuntimeException("Heap is empty()");
        }
        return heap.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public T poll() {
        if(heap.isEmpty()) {
            throw new RuntimeException("Heap is empty()");
        }

        T min = heap.getFirst();
        heap.set(0, heap.getLast());

        heap.removeLast();

        downHeap(0);

        return min;
    }

    private void upHeap(int index) {
        if(index == 0) return;
        int parentIndex = parent(index);

        if(heap.get(parentIndex).compareTo(heap.get(index)) > 0) {
            swap(parentIndex, index);
            upHeap(parentIndex);
        }
    }

    private void downHeap(int index) {
        int size = heap.size();

        int smallest = index;

        int left = (2 * index) + 1;
        int right = (2 * index) + 2;

        /*
        What Does compareTo() Return?

        compareTo() always returns an int:

        Return Value	    Meaning
        < 0	                This object is smaller
        = 0	                Both are equal
        > 0	                This object is greater
         */

        if(left < size && heap.get(left).compareTo(heap.get(smallest)) < 0) {
            smallest = left;
        }

        if(right <  size && heap.get(right).compareTo(heap.get(smallest)) < 0) {
            smallest = right;
        }

        if(smallest != index) {
            swap(smallest, index);
            downHeap(smallest);
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
