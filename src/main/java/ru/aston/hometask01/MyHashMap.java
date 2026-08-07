package ru.aston.hometask01;

public class MyHashMap<K, V> {
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

        private static final int DEFAULT_CAPACITY = 16;
        private Node<K, V>[] table;
        private int size = 0;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table =  new Node[DEFAULT_CAPACITY];
    }

    private int getBucketIndex(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode()) % table.length;
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        Node<K, V> head = table[index];

        while (head != null) {
            if ((key == null && head.key == null) || (key != null && key.equals(head.key))) {
                head.value = value;
                return;
            }
            head = head.next;
        }


        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = table[index];
        table[index] = newNode;
        size++;
    }


    public V get(K key) {
        int index = getBucketIndex(key);
        Node<K, V> head = table[index];


        while (head != null) {
            if ((key == null && head.key == null) || (key != null && key.equals(head.key))) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }


    public V remove(K key) {
        int index = getBucketIndex(key);
        Node<K, V> head = table[index];
        Node<K, V> prev = null;


        while (head != null) {
            if ((key == null && head.key == null) || (key != null && key.equals(head.key))) {

                if (prev != null) {
                    prev.next = head.next;
                } else {
                    table[index] = head.next;
                }
                size--;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null;
    }


    public int size() {
        return this.size;
    }


}
