package top.daoyang.linkedList;

public class LinkedList<E> {

    private Node dummyHead;

    private int size;

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
        if (index < 0 ||  index > size)
            throw new IllegalArgumentException("Require index < 0 ||  index > size");

            Node loopNode = dummyHead;

            for (int i = 0; i < index; i++)
                loopNode = loopNode.next;

            loopNode.next = new Node(e, loopNode.next);
            size ++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        if (index < 0 ||  index >= size)
            throw new IllegalArgumentException("Get fail require index < 0 ||  index >= size");

        Node loopNode = dummyHead.next;

        for (int i = 0; i < index; i++)
            loopNode = loopNode.next;

        return loopNode.data;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 ||  index >= size)
            throw new IllegalArgumentException("Set fail require index < 0 ||  index >= size");

        Node loopNode = dummyHead.next;

        for (int i = 0; i < index; i++)
            loopNode = loopNode.next;

        loopNode.data = e;
    }

    public boolean contains(E e) {
        Node loopNode = dummyHead.next;

        for (int i = 0; i < size - 1; i++) {
            if (loopNode.data.equals(e))
                return true;
            loopNode = loopNode.next;

        }
        return false;
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

        System.out.println(linkedList);
    }
}
