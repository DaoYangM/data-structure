public class DoubleEndNode<E> {
    public E data;

    public DoubleEndNode<E> prev;

    public DoubleEndNode<E> next;

    public DoubleEndNode(E data) {
        this(data, null, null);
    }

    public DoubleEndNode(E data, DoubleEndNode<E> prev, DoubleEndNode<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
