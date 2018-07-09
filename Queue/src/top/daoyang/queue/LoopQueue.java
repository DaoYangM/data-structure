package top.daoyang.queue;

public class LoopQueue<E> implements Queue<E> {

    private E[] data;

    private int front;

    private int tail;

    private int size;

    public LoopQueue(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.front = 0;
        this.tail = 0;
        this.size = 0;
    }

    public LoopQueue() {
        this(10);
    }


    public int getCapacity() {
        return data.length - 1;
    }

    public boolean isFull() {
        return (tail + 1) % (getCapacity() + 1) == front;
    }

    @Override
    public void enqueue(E e) {
        if (isFull())
            resize(data.length * 2);

        data[tail] = e;
        tail = (tail + 1) % (getCapacity() + 1);

        size ++;
    }

    private void resize(int i) {
        System.out.println("Resize: " + i);
        E[] newData = (E[]) new Object[i];

        for(int j = 0; j < size; j++) {
            newData[j] = data[(front + j) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;

    }

    @Override
    public E dequeue() {

        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty");

        E abandon = data[front];

        front = (front + 1) % (getCapacity() + 1);

        size --;

        if (size == (data.length  / 4) && data.length / 2 != 0)
            resize(data.length /2 + 1);
        return abandon;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty");

        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public String toString() {
        System.out.println("LoopQueue: size=" + getSize());

        StringBuilder start = new StringBuilder();
        start.append("front ");
        start.append("[");
        for (int i = 0; i < getSize(); i++) {
            start.append(data[(i + front) % data.length]);

            if (i != getSize() - 1)
                start.append(", ");
        }
        start.append(" ]");
        start.append(" tail");
        return start.toString();

    }

    public static void main(String[] args) {
        LoopQueue<Integer> loopQueue = new LoopQueue<>(5);

        loopQueue.enqueue(1);
        loopQueue.enqueue(1);
        loopQueue.dequeue();
        loopQueue.enqueue(1);
        loopQueue.dequeue();



        System.out.println(loopQueue);
    }
}
