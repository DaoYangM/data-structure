package top.daoyang.array;

public class Array<E> {

    private E[] data;
    private int size;

    public Array(int capacity) {
        this.data = (E[])new Object[capacity];
        this.size = 0;
    }

    public Array() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(E e) {
        if (size == data.length)
            resize(data.length *2);

        data[size] = e;
        size ++;
    }

    public void add(int index, E e) {
        if (size == data.length)
            resize(data.length *2);

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Array required index < 0 || index > size");

        for (int i = size; i > index; i --) {
            data[size] = data[size -1];
        }

        data[index] = e;
        size ++;
    }

    private void resize(int i) {
        E[] newArray = (E[]) new Object[i];

        for (int j = 0; j < size; j++) {
            newArray[j] = data[j];
        }

        data = newArray;
        System.out.println("Resize: " + i);
    }

    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Array required index < 0 || index > size");

        return data[index];
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Array required index < 0 || index > size");

        data[index] = e;
    }

    public boolean contains(E e) {
        for (E d : data) {
            if (e == d)
                return true;
        }
        return false;
    }

    public int search(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e)
                return i;
        }

        return -1;
    }

    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Delete failed. Array required index < 0 || index > size");

        E abandon = data[index];

        for (int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
        size --;

        // size  = 5 length = 10
        if (size < (data.length / 2))
            resize((data.length / 2));

        return abandon;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size -1);
    }

    public E removeElement(E e) {
        int findElement = search(e);

        if (findElement == -1) {
            return null;
        }
        return remove(findElement);
    }

    @Override
    public String toString() {
        System.out.println("Array: size=" + size + " capacity=" + getCapacity());

        StringBuilder start = new StringBuilder("[ ");
        for (int i = 0; i < size; i ++) {
            start.append(data[i]);

            if (i != size -1)
                start.append(", ");
        }
        start.append(" ]");

        return start.toString();
    }

    public static void main(String[] args) {
        Array array = new Array(5);

        array.addLast(0);
        array.addLast(1);
        array.addLast(2);
        array.addLast(3);
        array.addLast(4);
        array.addLast(5);

        System.out.println(array);

        Array resizeArray = new Array(10);

        resizeArray.addLast(1);
        resizeArray.addLast(2);
        resizeArray.addLast(3);
        resizeArray.addLast(4);
        resizeArray.addLast(5);

        resizeArray.removeLast();

        System.out.println(resizeArray);
    }
}
