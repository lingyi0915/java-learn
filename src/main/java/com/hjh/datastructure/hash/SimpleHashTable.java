package com.hjh.datastructure.hash;

/**
 * @author hjh
 * @date 2019/12/26
 * {@link java.util.Hashtable}
 * {@link java.util.HashMap}
 * 简单散列表
 */
public class SimpleHashTable<K,V> {
    private static class Node<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public int getHash() {
            return hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }
    }
}
