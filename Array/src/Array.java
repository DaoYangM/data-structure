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
            throw new IllegalArgumentException("AddLast failed. Array is full.");

        data[size] = e;
        size ++;
    }

    public void add(int index, E e) {
        if (size == data.length)
            throw new IllegalArgumentException("Add failed. Array is full.");

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Array required index < 0 || index > size");

        for (int i = size; i > index; i --) {
            data[size] = data[size -1];
        }

        data[index] = e;
        size ++;
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

        for (int i = size -1; i > index; i--) {
            data[i - 1] = data[i];
        }
        size --;

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

        StringBuilder start = new StringBuilder("[");
        for (int i = 0; i < size; i ++) {
            start.append(data[i]);

            if (i != size -1)
                start.append(", ");
        }
        start.append("]");

        return start.toString();
    }

    public static void main(String[] args) {
        Array array = new Array();

        array.addLast(0);
        array.addLast(2);
        array.add(1, 1);

        System.out.println(array.contains(100));
        System.out.println(array.search(1));
        System.out.println(array.remove(1));
        System.out.println(array.removeElement(200));

        System.out.println(array);
    }
}
