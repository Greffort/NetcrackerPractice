package com.greffort.java_se.linkedList;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class DoubleLinkedList<E> implements Serializable, Iterable<E> {
    private class Node<E> implements Serializable {
        E data;
        Node<E> next;
        Node<E> prev;

        Node(E element) {
            this.data = element;
        }
    }

    private Node first;

    private int size = 0;

    public int getSize() {
        return size;
    }

    public E getNode(final int index) {
        Node<E> x = first;
        if (first == null) {
            return null;
        }
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x.data;
    }

    public void setNode(final E e, final int index) {
        Node<E> x = first;
        if (first == null) {
            return;
        }
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        x.data = e;

    }

    public void addNode(final E e, final int index) {
        if (first == null) {//добавление если список пуст
            addFirst(e);
            size++;
            return;
        }
        if (index == size) {//добавление в конец списка
            addLast(e, index);
            size++;
            return;
        }
        if (index == 0) {//добавление в начало списка
            addFront(e);
            size++;
            return;
        } else {//добавление по индексу
            addIndex(e, index);
            size++;
            return;
        }
    }

    private void addFirst(final E e) {
        Node newNode = new Node(e);
        first = newNode;
        first.next = newNode;
        first.prev = newNode;
    }

    private void addFront(final E e) {
        Node newNode = new Node(e);
        Node<E> x = first;
        newNode.next = first;
        newNode.prev = first.prev;
        first.prev = newNode;
        first = newNode;
        for (int i = 0; i < size - 1; i++) {
            x = x.next;
        }
        x.next = newNode;
    }

    private void addLast(final E e, final int index) {
        Node newNode = new Node(e);
        Node<E> x = first;
        for (int i = 0; i < index - 1; i++) {
            x = x.next;
        }
        first.prev = newNode;
        x.next = newNode;
        newNode.next = first;
        newNode.prev = x;
    }

    private void addIndex(final E e, final int index) {
        Node<E> x = first;
        Node<E> temp = first;
        Node newNode = new Node(e);
        for (int i = 0; i < index - 1; i++) {
            x = x.next;
        }
        newNode.next = x.next;
        newNode.prev = x.next.prev;
        x.next.prev = newNode;
        x.next = newNode;

    }

    public void removeNode(final int index) {
        Node<E> x = first;
        if (first == null) {
            return;
        } else {
            if (index == 0) {
                x = x.prev;
                first = first.next;
                x.next = first;
                first.prev = x;
            } else {
                for (int i = 0; i < index - 1; i++) {
                    x = x.next;
                }
                x.next = x.next.next;
                x = x.next;
                x.prev = x.prev.prev;
            }
        }
        size--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleLinkedList<?> that = (DoubleLinkedList<?>) o;
        return size == that.size &&
                Objects.equals(first, that.first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, size);
    }

    public Iterator<E> iterator() {
        return new Iterator<E>();
    }

    private class Iterator<E> implements java.util.Iterator<E>, Serializable {

        private int index = 0;

        Node<E> x = first;
        boolean isFirst = true;

        public boolean hasNext() {
            if (first == null) {
                return false;
            } else {
                return index < getSize();
            }
        }

        public E next() throws NoSuchElementException {
            if (isFirst) {
                isFirst = false;
                index++;
                return (E) first.data;
            }
            x = x.next;
            index++;
            return x.data;
        }

    }
}
