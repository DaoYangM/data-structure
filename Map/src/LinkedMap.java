import java.util.List;

public class LinkedMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Node<K, V> dummyHead;

    private int size;

    private Node delNode;

    public LinkedMap() {
        this.dummyHead = new Node<>();

        delNode = null;
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        if (contains(key))
            return;

        Node loopNode = dummyHead;

        while (loopNode.next != null)
            loopNode = loopNode.next;

        loopNode.next = new Node<>(key, value);

        size++;
    }

    @Override
    public boolean contains(K key) {

        Node loopNode = dummyHead.next;

        while (loopNode != null) {
            if (loopNode.data.equals(key))
                return true;

            loopNode = loopNode.next;
        }

        return false;
    }

    @Override
    public V get(K k) {
        Node findNode = find(k);

        if (findNode != null)
            return (V) findNode.value;

        return null;
    }

    public Node find(K k) {

        Node<K ,V> loopNode = dummyHead.next;

        while (loopNode != null) {
            if (loopNode.data.equals(k))
                return loopNode;

            loopNode = loopNode.next;
        }

        return null;
    }


    @Override
    public void set(K k, V v) {
        Node findNode = find(k);

        if (findNode != null)
            findNode.value = v;
        else
            throw new IllegalArgumentException("K doesn't existed");
    }

    @Override
    public V remove(K k) {
        size --;
        Node head = dummyHead;

        remove(head, k);
        if (delNode == null)
            throw new IllegalArgumentException("K doesn't existed");

        return (V) delNode.value;

    }

    private Node remove(Node node, K k) {

        if (node == null)
            throw new IllegalArgumentException("K doesn't existed");

        Node returnNode = null;
        if (node.data != null && node.data.equals(k)) {
            delNode = node;
            return node;
        }
        else
            returnNode = remove(node.next, k);

        if (returnNode != null) {
            node.next = delNode.next;

            delNode.next = null;
        }

        return null;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class Node<K, V> {
        private K data;

        private V value;

        private Node next;

        public Node(K data, V vale, Node next) {
            this.data = data;
            this.value = vale;
            this.next = next;
        }

        public Node(K data, V value) {
            this(data, value, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public static void main(String[] args) {
        LinkedMap<Integer, String> linkedMap = new LinkedMap<>();

        linkedMap.add(1, "ye");
        linkedMap.add(2, "de");
        linkedMap.add(3, "yang");
        linkedMap.add(3, "yang");

        System.out.println(linkedMap.remove(3));
    }
}
