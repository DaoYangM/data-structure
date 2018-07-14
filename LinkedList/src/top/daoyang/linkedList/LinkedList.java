package top.daoyang.linkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedList<E> {

    private Node dummyHead;

    private int size;

    private class Node<E> {
        private E data;

        private Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(E data) {
            this(data, null);
        }

        public Node() {
            this(null, null);
        }

        public Node generate(List<Integer> lists) {

            if (lists == null || lists.size() == 0)
                throw new IllegalArgumentException("lists == null || lists.size() == 0");

            Node head = null, loopNode = null;

            for (Integer item : lists) {

                if (head == null) {
                    Node<Integer> node = new Node<>(item);
                    head = node;
                    loopNode = head;
                } else {
                    Node<Integer> node = new Node<>(item);
                    loopNode.next = node;
                    loopNode = node;
                }
            }

            return head;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node deleteAllTheSame(Node head, E val) {
        while (head != null && head.data == val) {
            Node delNode = head;
            head = head.next;
            delNode.next = null;
        }

        Node pre = head;

        while (pre != null) {
            if (pre.next != null && pre.next.data == val) {
                Node delNode = pre.next;
                pre.next = delNode.next;
                delNode.next = null;
            } else {
                pre = pre.next;
            }
        }

        return head;
    }

    public LinkedList() {
        dummyHead = new Node(null, null); // virtual dummyHead node
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Require index < 0 ||  index > size");

        Node loopNode = dummyHead;

        for (int i = 0; i < index; i++)
            loopNode = loopNode.next;

        loopNode.next = new Node(e, loopNode.next);
        size++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get fail require index < 0 ||  index >= size");

        Node loopNode = dummyHead.next;

        for (int i = 0; i < index; i++)
            loopNode = loopNode.next;

        return (E) loopNode.data;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set fail require index < 0 ||  index >= size");

        Node loopNode = dummyHead.next;

        for (int i = 0; i < index; i++)
            loopNode = loopNode.next;

        loopNode.data = e;
    }

    public boolean contains(E e) {
        Node loopNode = dummyHead.next;

        for (int i = 0; i < size; i++) {
            if (loopNode.data.equals(e))
                return true;
            loopNode = loopNode.next;

        }
        return false;
    }

    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove fail require index < 0 ||  index >= size");

        Node prevNode = dummyHead;

        for (int i = 0; i < index; i++)
            prevNode = prevNode.next;

        Node delNode = prevNode.next;
        prevNode.next = prevNode.next.next;

        delNode.next = null;

        size--;

        return (E) delNode.data;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public E removeByElem(E e) {
        return remove(getIndex(dummyHead.next, e, -1));
    }

    private int getIndex(Node root, E e, int index) {
        if (root != null) {
            index ++;
            if (root.data.equals(e))
                return index;

            return getIndex(root.next, e, index);
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("LinkedList: ");

        Node loopNode = dummyHead.next;
        while (loopNode != null) {
            stringBuilder.append(loopNode.data.toString());
            stringBuilder.append(" -> ");
            loopNode = loopNode.next;
        }

        stringBuilder.append(" NULL");

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(5);
        linkedList.addFirst(0);

        System.out.println("Contains: " + linkedList.contains(0));
        System.out.println("Get 0 : " + linkedList.get(0));

        linkedList.set(4, 4);

        linkedList.removeFirst();
        linkedList.removeLast();
        linkedList.remove(1);

        System.out.println(linkedList);
        System.out.println(linkedList.removeByElem(1));

        System.out.println(linkedList);
    }
}
