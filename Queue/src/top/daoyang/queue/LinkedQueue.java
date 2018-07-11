package top.daoyang.queue;

public class LinkedQueue<E> implements Queue<E> {

    private Node head;

    private Node tail;

    private int size;

    public LinkedQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        Node node = new Node(e);

        if (tail == null) {
            tail = node;
            head = tail;
        } else {
            tail.next = node;
            tail = node;
        }

        size++;
    }

    @Override
    public E dequeue() {

        if (head == null)
            throw new IllegalArgumentException("Queue is empty");

        Node abandonNode = head;

        head = head.next;

        abandonNode.next = null;

        size--;

        return abandonNode.data;
    }

    @Override
    public E getFront() {
        return head.data;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
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

        @Override
        public String toString() {
            return data.toString();
        }
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder("LinkedQueue: head: ");
        for (Node cur = head ;cur != null; cur = cur.next) {
            stringBuilder.append(cur.data);
            stringBuilder.append(" -> ");
        }
        stringBuilder.append(" NULL tail");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();

        linkedQueue.enqueue(0);
        linkedQueue.enqueue(1);
        linkedQueue.enqueue(2);

        linkedQueue.dequeue();

        System.out.println(linkedQueue);
    }
}
