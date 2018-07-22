import top.daoyang.queue.LinkedQueue;
import top.daoyang.stack.LinkedStack;

import java.util.Arrays;
import java.util.List;

public class AVL<E extends Comparable<E>> {

    private TreeNode root;

    private int size;

    private boolean isDeleteRoot = false;

    public AVL() {
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

        private int height;

        public TreeNode(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1;
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

    private int getHeight(TreeNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBalanceFactor(TreeNode root) {
        if (root == null)
            return 0;
        return getHeight(root.left) - getHeight(root.right);
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
            size++;

            return new TreeNode(e);
        }

        if (e.compareTo(root.data) < 0) {
            root.left = add2(root.left, e);
        }

        if (e.compareTo(root.data) > 0) {
            root.right = add2(root.right, e);
        }

        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;

        int balanceFactor = getBalanceFactor(root);

        if (balanceFactor > 1 && getBalanceFactor(root.left) >= 0)
            return rightRotate(root);

        if (balanceFactor < -1 && getBalanceFactor(root.right) <= 0)
            return leftRotate(root);

        System.out.println("balance " + getBalanceFactor(root));
        return root;
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T3 = x.right;

        x.right = y;
        y.left = T3;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private TreeNode leftRotate(TreeNode y) {
        TreeNode x = y.right;
        TreeNode T2 = x.left;

        x.left = y;
        y.right = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    public boolean contains(E e) {

        if (e == null || root == null)
            return false;
        return contains(root, e);
    }

    public boolean contains(TreeNode treeNode, E e) {
        if (treeNode == null)
            return false;
        if (treeNode.data.compareTo(e) == 0)
            return true;
        else if (e.compareTo(treeNode.data) < 0)
            return contains(treeNode.left, e);
        else if (e.compareTo(treeNode.data) > 0)
            return contains(treeNode.right, e);

        return false;
    }

    public TreeNode findRightMax(TreeNode root) {
        if (root.right != null)
            return findRightMax(root.right);
        return root;
    }

    private TreeNode findRightMin(TreeNode root) {
        if (root.left != null)
            return findRightMin(root.left);
        return root;
    }

    public static AVL buildTree(List<Integer> list) {
        AVL<Integer> AVL = new AVL<>();

        for (Integer item : list) {
            AVL.add2(item);
        }

        return AVL;
    }

    public void remove(E e) {
        if (e.compareTo(root.data) == 0) {
            TreeNode dummyRoot = new TreeNode(null);
            dummyRoot.left = root;
            root = dummyRoot;

            isDeleteRoot = true;
            root = remove(root, e);
        } else {
            remove(root, e);
        }

        size--;
    }

    private TreeNode remove(TreeNode root, E e) {
        if (root.left != null && e.compareTo(root.left.data) == 0) {
            if (root.left.left == null && root.left.right == null)
                root.left = null;
            else if (root.left.left != null && root.left.right == null) {
                root.left = root.left.left;
            } else if (root.left.left == null && root.left.right != null) {
                root.left = root.left.right;
            } else if (root.left.left != null && root.left.right != null) {
                TreeNode delNode = root.left;
                TreeNode rMax = findRightMax(delNode.left);
                rMax.right = delNode.right;

                if (isDeleteRoot) {
                    root.left.right = null;
                    root = root.left.left;
                    isDeleteRoot = false;
                } else {
                    root.left = delNode.left;
                }
            }
        } else if (e.compareTo(root.right.data) == 0) {
            if (root.right.left == null && root.right.right == null)
                root.right = null;
            else if (root.right.left != null && root.right.right == null) {
                root.right = root.right.left;
            } else if (root.right.left == null && root.right.right != null) {
                root.right = root.right.right;
            } else if (root.right.left != null && root.right.right != null) {
                TreeNode delNode = root.right;
                TreeNode rMax = findRightMin(delNode.right);
                rMax.left = delNode.left;
                root.right = delNode.right;
            }
        } else if (e.compareTo(root.left.data) < 0 || (e.compareTo(root.data) < 0))
            remove(root.left, e);
        else if ((e.compareTo(root.right.data) > 0) || (e.compareTo(root.data) > 0))
            remove(root.right, e);

        return root;
    }

    public void preOrderNR() {
        LinkedStack<TreeNode> linkedStack = new LinkedStack<>();

        linkedStack.push(root);
        while (!linkedStack.isEmpty()) {

            TreeNode pNode13 = linkedStack.pop();
            if (pNode13 != null) {
                System.out.print(pNode13.data + "\t");

                linkedStack.push(pNode13.right);
                linkedStack.push(pNode13.left);
            }
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(TreeNode root) {
        if (root == null)
            return;
        System.out.print(root.data + "\t");
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(TreeNode root) {
        if (root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);
    }

    public void levelOrder() {
        if (root == null)
            throw new IllegalArgumentException("AVL is empty");

        LinkedQueue<TreeNode> linkedQueue = new LinkedQueue<>();
        linkedQueue.enqueue(root);

        while (!linkedQueue.isEmpty()) {
            TreeNode deNode = linkedQueue.dequeue();

            if (deNode != null) {
                System.out.println(deNode.data + "\t");

                linkedQueue.enqueue(deNode.left);
                linkedQueue.enqueue(deNode.right);
            }
        }
    }

    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("AVL is empty");
        return minimum(root).data;
    }

    private TreeNode minimum(TreeNode node) {

        if (node == null)
            return null;

        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    public TreeNode removeMin() {
        TreeNode delNode = minimum(root);
        if (size == 0)
            throw new IllegalArgumentException("AVL is empty");

        root = removeMin(root);
        size--;

        return delNode;
    }

    private TreeNode removeMin(TreeNode node) {
        if (node == null)
            return null;

        if (node.left == null) {

            TreeNode rightNode = node.right;
            node.right = null;

            return rightNode;
        }

        node.left = removeMin(node.left);

        return node;
    }

    public E maximum() {
        if (size == 0)
            throw new IllegalArgumentException("AVL is empty");
        return maximum(root).data;
    }

    private TreeNode maximum(TreeNode node) {

        if (node == null)
            return null;

        if (node.right == null)
            return node;

        return maximum(node.right);
    }

    public TreeNode removeMax() {
        TreeNode delNode = maximum(root);

        root = removeMax(root);
        size--;
        return delNode;
    }

    private TreeNode removeMax(TreeNode node) {
        if (node == null)
            return null;

        if (node.right == null) {

            TreeNode leftNode = node.left;
            node.left = null;

            return leftNode;
        }

        node.right = removeMin(node.left);

        return node;
    }

    public void hRemove(E e) {
        size--;
        root = hRemove(root, e);
    }

    private TreeNode hRemove(TreeNode node, E e) {
        if (node == null)
            return null;

        if (e.compareTo(node.data) < 0)
            node.left = hRemove(node.left, e);

        else if (e.compareTo(node.data) > 0) {
            node.right = hRemove(node.right, e);
        } else {
            if (node.left == null) {
                TreeNode rightNode = node.right;

                node.right = null;

                return rightNode;

            }

            if (node.right == null) {
                TreeNode leftNode = node.left;

                node.left = null;

                return leftNode;
            }

            TreeNode successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }

        return node;
    }

    public static void main(String[] args) {
        AVL avl = AVL.buildTree(Arrays.asList(11, 6, 4, 8, 22, 44, 55, 66, 77, 88, 99, 100, 110));

    }
}
