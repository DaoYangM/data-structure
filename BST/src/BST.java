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

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.add(11);
        bst.add(10);
        bst.add(9);
        bst.add(12);
        bst.add(14);
        bst.add(11);

        System.out.println(bst.root.left);
    }
}
