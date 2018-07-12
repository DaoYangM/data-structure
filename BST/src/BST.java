public class BST<E extends Comparable<E>> {

    private TreeNode root;

    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class TreeNode {
        private E data;

        private TreeNode left;

        private TreeNode right;

        public TreeNode(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public void add(E e) {
        if (root == null) {
            root = new TreeNode(e);
            size++;
        } else
            add(root, e);
    }

    private void add(TreeNode root, E e) {

        if (root.left == null && (e.compareTo(root.data) < 0)) {
            root.left = new TreeNode(e);
            size++;

            return;
        }

        if (root.right == null && (e.compareTo(root.data) > 0)) {
            root.right = new TreeNode(e);
            size++;

            return;
        }

        if (e.compareTo(root.data) < 0) {
            add(root.left, e);
        }

        if (e.compareTo(root.data) > 0) {
            add(root.right, e);
        }
    }

    public void add2(E e) {
        root = add2(root, e);
    }

    public TreeNode add2(TreeNode root, E e) {
        if (root == null) {
            size ++;

            return new TreeNode(e);
        }

        if (e.compareTo(root.data) < 0) {
            root.left = add2(root.left, e);
        }

        if (e.compareTo(root.data) > 0) {
            root.right = add2(root.right, e);
        }

        return root;
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.add2(11);
        bst.add2(10);
        bst.add2(12);
        bst.add2(9);
     }
}
