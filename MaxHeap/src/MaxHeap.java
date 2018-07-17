import top.daoyang.array.Array;

import java.util.Arrays;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        this.data = new Array<>(capacity);
    }

    public MaxHeap() {
        this.data = new Array<>();
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public E getFront() {
        if (getSize() == 0)
            throw new IllegalArgumentException("Heap is null");
        return data.get(0);
    }

    public int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index 0 doesn't have parent");
        return (index - 1) / 2;
    }

    public int leftChild(int index) {
        return (index * 2) + 1;
    }

    public int rightChild(int index) {
        return (index * 2) + 2;
    }

    public void add(E e) {
        data.addLast(e);

        siftUp(data.getSize() - 1);
    }

    private void siftUp(int i) {
        if (i == 0)
            return;
        int pi = parent(i);
        while (i > 0 && data.get(i).compareTo(data.get(pi)) > 0) {
            data.swap(i, pi);
            i = pi;
        }
    }

    public E extractMax() {
        E max = getFront();

        data.set(0, data.get(getSize() - 1));
        data.removeLast();
        siftDown();

        return max;
    }

    private void siftDown() {
        int ki = 0;

        while (leftChild(ki) < getSize()) {
            if (rightChild(ki) < getSize() && data.get(rightChild(ki)).compareTo(data.get(leftChild(ki))) > 0) {
                data.swap(ki, rightChild(ki));
                ki = rightChild(ki);
            }

            else {
                data.swap(ki, leftChild(ki));
                ki = leftChild(ki);
            }
        }
    }

    public static<E extends Comparable<E>> MaxHeap generate(List<E> lists) {
        MaxHeap<E> maxHeap = new MaxHeap<>();
        for (E item: lists) {
            maxHeap.add(item);
        }

        return maxHeap;
    }

    public static void main(String[] args) {
//        MaxHeap maxHeap = generate(Arrays.asList(62, 52, 30, 28, 41, 22, 13, 19, 17, 15, 16));
        MaxHeap maxHeap = generate(Arrays.asList(62, 52));
        for (int i = 0; i < 2; i++) {
            System.out.println(maxHeap.extractMax());
        }
    }
}
