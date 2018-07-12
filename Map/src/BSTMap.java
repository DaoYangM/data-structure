public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private TreeNode root;

    private int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(K e, V v) {
        root = add(root, e, v);
    }

    public TreeNode add(TreeNode root, K e, V v) {
        if (root == null) {
            size ++;
            return new TreeNode(e, v);
        }

        if (e.compareTo(root.data) < 0)
            root.left = add(root.left, e, v);

        else if (e.compareTo(root.data) > 0)
            root.right = add(root.right, e, v);
        else
            root.vale = v;
        return root;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K k) {
        TreeNode treeNode = getNode(root, k);
        return treeNode == null? null: treeNode.vale;
    }

    @Override
    public void set(K k, V v) {
        TreeNode node = getNode(root, k);

        if (node == null)
            throw new IllegalArgumentException("Can't find key" + k);

        node.vale = v;
    }

    public TreeNode getNode(TreeNode node, K k) {
        if (node == null)
            return null;

        if (k.compareTo(node.data) == 0)
            return node;

        else if (k.compareTo(node.data) < 0)
            return getNode(node.left, k);

        else if (k.compareTo(node.data) > 0)
            return getNode(node.right, k);
        else
            return null;
    }

    private class TreeNode {
        private K data;

        private V vale;
        
        private TreeNode left;

        private TreeNode right;

        public TreeNode(K data, V vale) {
            this.data = data;
            this.vale = vale;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public static void main(String[] args) {
        BSTMap<Integer, String> map = new BSTMap<>();

        map.add(11, "OK");
        map.add(10, "NO");

        System.out.println(map.get(10));
    }
}
