public class DoubleEndLinkedList<E> {

    private DoubleEndNode<E> dummyHead;

    private DoubleEndNode<E> tail;

    private int size;

    public DoubleEndLinkedList() {
        this(null, null, 0);
    }

    private DoubleEndLinkedList(DoubleEndNode<E> dummyHead, DoubleEndNode<E> tail, int size) {
        this.dummyHead = new DoubleEndNode<>(null);
        this.tail = tail;
        this.size = size;
    }

    public  void add(int index, E data) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failure required index < 0 || index > size");

        DoubleEndNode<E> loopNode = dummyHead;
        DoubleEndNode<E> newNode = new DoubleEndNode<>(data);

        for (int i = 0; i < index; i++)
            loopNode = loopNode.next;

        if (size == index) {
            loopNode.next = newNode;
            newNode.prev = loopNode;
        } else {
            newNode.next = loopNode.next;
            newNode.prev = loopNode;
            loopNode.next.prev = newNode;
            loopNode.next = newNode;
        }

        size++;
    }

    public E remove(int index){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Remove failure required index < 0 || index > size");

        DoubleEndNode<E> loopNode = dummyHead;

        for (int i = 0; i < index; i++)
            loopNode = loopNode.next;

        DoubleEndNode<E> delNode = loopNode.next;

        if (size == index + 1) {
            loopNode.next = null;
        } else {
            loopNode.next = delNode.next;
            delNode.next.prev = loopNode;
        }

        delNode.next = null;
        delNode.prev = null;

        size --;
        return delNode.data;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("DoubleEndLinkedList's size: " + size + "\n");

        DoubleEndNode<E> loopNode = dummyHead.next;
        while (loopNode != null) {
            stringBuilder.append(loopNode.data);
            stringBuilder.append(" <-----> ");

            loopNode = loopNode.next;
        }

        stringBuilder.append("NULL");

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        DoubleEndLinkedList<Integer> linkedList = new DoubleEndLinkedList<>();

        linkedList.add(0, 1);
        linkedList.add(1, 2);
        linkedList.add(2, 3);
        linkedList.add(2, 4);

        linkedList.remove(2);
        linkedList.remove(1);
        linkedList.remove(0);
        linkedList.remove(0);

        System.out.println(linkedList);
    }
}
