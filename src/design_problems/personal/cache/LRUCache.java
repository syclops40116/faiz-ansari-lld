package design_problems.personal.cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private static class Node<K, V> {

        K key;
        V value;

        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;

    private final Map<K, Node<K, V>> map;

    private final Node<K, V> head;
    private final Node<K, V> tail;

    public LRUCache(int capacity) {

        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be > 0");
        }

        this.capacity = capacity;
        this.map = new HashMap<>();

        head = new Node<>(null, null);
        tail = new Node<>(null, null);

        head.next = tail;
        tail.prev = head;
    }

    // ========================
    // GET
    // ========================
    public V get(K key) {

        Node<K, V> node = map.get(key);

        if (node == null) {
            return null;
        }

        moveToFront(node);

        return node.value;
    }

    // ========================
    // PUT
    // ========================
    public void put(K key, V value) {

        Node<K, V> node = map.get(key);

        // Case 1: Key exists
        if (node != null) {

            node.value = value;
            moveToFront(node);
            return;
        }

        // Case 2: New key
        if (map.size() == capacity) {

            Node<K, V> lru = tail.prev;

            removeNode(lru);
            map.remove(lru.key);
        }

        Node<K, V> newNode = new Node<>(key, value);

        addToFront(newNode);
        map.put(key, newNode);
    }

    // ========================
    // DLL Helpers
    // ========================

    private void addToFront(Node<K, V> node) {

        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node<K, V> node) {

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToFront(Node<K, V> node) {

        removeNode(node);
        addToFront(node);
    }

    public int size() {
        return map.size();
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }
}