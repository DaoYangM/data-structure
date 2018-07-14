public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bstTree;

    public BSTSet() {
        this.bstTree = new BST<>();
    }

    @Override
    public void add(E e) {
        bstTree.add(e);

    }

    @Override
    public void remove(E e) {
        bstTree.remove(e);
    }

    @Override
    public int getSize() {
        return bstTree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bstTree.getSize() == 0;
    }

    @Override
    public boolean contains(E e) {
        return bstTree.contains(e);
    }

    public static void main(String[] args) {
        BSTSet<Integer> bstSet = new BSTSet<>();

        bstSet.add(10);
        bstSet.add(20);
        bstSet.add(30);

        System.out.println(bstSet.getSize());

        bstSet.remove(20);

        bstSet.bstTree.preOrder();

        System.out.println(bstSet.contains(10));

        System.out.println(bstSet.isEmpty());
    }
}
