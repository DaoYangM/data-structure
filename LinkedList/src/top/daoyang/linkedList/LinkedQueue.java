package top.daoyang.linkedList;

import top.daoyang.queue.Queue;

public class LinkedQueue<E> implements Queue<E> {

    private LinkedList<E> linkedList;

    public LinkedQueue() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void enqueue(E e) {

    }

    @Override
    public E dequeue() {
        return null;
    }

    @Override
    public E getFront() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
