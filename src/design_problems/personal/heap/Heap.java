package design_problems.personal.heap;

public interface Heap<T> {
    void add(T val);
    T poll();
    T peek();
    boolean isEmpty();
}
