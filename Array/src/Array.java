public class Array {

    private int[] data;
    private int size;

    public Array(int capacity) {
        this.data = new int[capacity];
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

    public void addLast(int e) {
        if (size == data.length)
            throw new IllegalArgumentException("AddLast failed. Array is full.");

        data[size] = e;
        size ++;
    }

    public void add(int index, int e) {
        if (size == data.length)
            throw new IllegalArgumentException("AddLast failed. Array is full.");

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Array required index < 0 || index > size");

        for (int i = size; i > index; i --) {
            data[size] = data[size -1];
        }

        data[index] = e;
        size ++;
    }

    public int get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Add failed. Array required index < 0 || index > size");

        return data[index];
    }

    public void set(int index, int e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Add failed. Array required index < 0 || index > size");

        data[index] = e;
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

        System.out.println(array);
    }
}
