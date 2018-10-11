package com.greffort.linkedList;

//import com.greffort.interfaces.Space;

public final class SingleLinkedList<E> {

    class Node<E> {
        E data;
        Node<E> next;

        //Node<E> prev; Для двусвязного списка
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
    }

    private void addFront(final E e) {
        Node newNode = new Node(e);
        Node<E> x = first;
        newNode.next = first;
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
        x.next = newNode;
        newNode.next = first;
    }

    private void addIndex(final E e, final int index) {
        Node<E> x = first;
        Node<E> temp = first;
        Node newNode = new Node(e);
        for (int i = 0; i < index - 1; i++) {
            x = x.next;
        }
        temp = x.next;
        x.next = newNode;
        newNode.next = temp;
    }

    public void removeNode(final int index) {
        Node<E> x = first;
        if (first == null) {
            return;
        } else {
            if (index == 0) {
                for (int i = 0; i < size - 1; i++) {
                    x = x.next;
                }
                x.next = first.next;
                first = first.next;
            } else {
                for (int i = 0; i < index - 1; i++) {
                    x = x.next;
                }
                x.next = x.next.next;
            }
        }
        size--;
    }
}

