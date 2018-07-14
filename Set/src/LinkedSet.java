import top.daoyang.linkedList.LinkedList;

public class LinkedSet<E> implements Set<E>{

    private LinkedList<E> linkedList;

    public LinkedSet() {
        this.linkedList = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (contains(e))
            return;
        linkedList.addLast(e);
    }

    @Override
    public void remove(E e) {
        linkedList.removeByElem(e);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }

    public static void main(String[] args) {
        LinkedSet<Integer> linkedSet = new LinkedSet<>();

        linkedSet.add(1);
        linkedSet.add(2);
        linkedSet.add(3);
        linkedSet.add(3);

        System.out.println(linkedSet);

        linkedSet.remove(3);

        System.out.println(linkedSet);
    }
}
