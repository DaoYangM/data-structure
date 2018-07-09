package top.daoyang.queue;


import top.daoyang.array.Array;

public class ArrayQueue<E> implements Queue<E> {

    private Array<E> data;

    public ArrayQueue() {
        data = new Array<>();
    }

    @Override
    public void enqueue(E e) {
        data.addLast(e);
    }

    @Override
    public E dequeue() {
        return data.removeFirst();
    }

    @Override
    public E getFront() {
        return data.get(0);
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public String toString() {
        System.out.println("QueueArray: size=" + getSize());

        StringBuilder start = new StringBuilder();
        start.append("front ");
        start.append("[");
        for (int i = 0; i < getSize(); i++) {
            start.append(data.get(i));

            if (i != getSize() - 1)
                start.append(", ");
        }
        start.append(" ]");
        start.append(" tail");
        return start.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();

        arrayQueue.enqueue(1);
        arrayQueue.enqueue(2);
        arrayQueue.enqueue(3);
        arrayQueue.dequeue();

        System.out.println(arrayQueue);
    }
}
