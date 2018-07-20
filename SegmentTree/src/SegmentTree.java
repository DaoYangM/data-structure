public class SegmentTree<E> {
    private E[] data;

    private E[] tree;

    private Merge<E> merge;

    public SegmentTree(E[] arr, Merge<E> merge) {
        this.data = (E[] )new Object[arr.length];

        this.merge = merge;

        for (int i = 0; i < data.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[4 * arr.length];


        buildSegmentTree(0, 0, arr.length - 1);
    }

    public void buildSegmentTree(int i, int l, int r) {

        if (l == r) {
            tree[i] = data[l];
            return;
        }

        int LCI = leftChild(i);
        int RCI = rightChild(i);

        int mid = (l + r) / 2;

        buildSegmentTree(LCI, l, mid);
        buildSegmentTree(RCI, mid + 1, r);

        tree[i] = merge.merge(tree[LCI], tree[RCI]);
    }

    public E query(int QL, int QR) {
        if (QL < 0 || QR >= data.length)
            throw new IllegalArgumentException("out of index");

        return query(0, 0, data.length - 1, QL, QR);
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR) {

        if (l == queryL && r == queryR)
            return tree[treeIndex];

        int mid = (l + r) / 2 ;
        int LCI = leftChild(treeIndex);
        int RCI = rightChild(treeIndex);

        if (queryL >= mid + 1)
            return query(RCI, mid + 1, r, queryL, queryR);

        else if (queryR <= mid)
            return query(LCI, l, mid, queryL, queryR);

        E leftResult = query(leftChild(treeIndex), l, mid, queryL, mid);
        E rightResult = query(rightChild(treeIndex), mid +1, r, mid + 1, queryR);

        return merge.merge(leftResult, rightResult);
    }

    public void set(int index, E e) {

        if (index < 0 || index >= getSize())
            throw new IllegalArgumentException("index out of range");

        data[index] = e;

        set(0, 0, tree.length, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {

        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = (r + l) / 2;
        int rightChild = rightChild(treeIndex);
        int leftChild = leftChild(treeIndex);

        if (index >= mid + 1)
            set(rightChild, mid + 1, r, index, e);

        if (index <= mid)
            set(leftChild, l, mid, index, e);

        merge.merge(tree[leftChild], tree[rightChild]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        return data[index];
    }

    public int leftChild(int index) {
        return 2 * index + 1;
    }

    public int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("SegmentTree: [");
        for (int i = 0; i < tree.length; i ++) {
            if (tree[i] != null)
                stringBuilder.append(tree[i].toString());
            else
                stringBuilder.append("null");

            if (i != tree.length - 1)
                stringBuilder.append(", ");
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @FunctionalInterface
    private interface Merge<E> {
        E merge(E a, E b);
    }

    public static void main(String[] args) {
        Integer[] nums = {2, 2, 3, 4, 5};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);

        System.out.println(segmentTree.query(0, 4));
    }
}
