import top.daoyang.queue.LinkedQueue;
import top.daoyang.stack.LinkedStack;

import java.util.Arrays;
import java.util.List;

public class RBTree<E extends Comparable<E>> {

    private TreeNode root;

    private int size;

    private boolean isDeleteRoot = false;

    public RBTree() {
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

        private Color color;

        public TreeNode(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.color = Color.RED;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private enum Color {
        RED,
        BLACK;
    }

    private boolean isRed(TreeNode node) {
        if (node == null)
            return false;
        return node.color == Color.RED;
    }

    private TreeNode leftRotate(TreeNode node) {
        TreeNode x = node.right;
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = Color.RED;

        return x;
    }

    private TreeNode rightRotate(TreeNode node) {
        TreeNode x = node.left;
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = Color.RED;

        return x;
    }

    private void flipColors(TreeNode node) {
        node.color = Color.RED;
        node.left.color = Color.BLACK;
        node.right.color = Color.BLACK;
    }

    public void add(E e) {
        root = add(root, e);
        root.color = Color.BLACK;
    }

    public TreeNode add(TreeNode node, E e) {
        if (node == null) {
            size ++;

            return new TreeNode(e);
        }

        if (e.compareTo(node.data) < 0) {
            node.left = add(node.left, e);
        }

        else if (e.compareTo(node.data) > 0) {
            node.right = add(node.right, e);
        }

        if (isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);

        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);

        if (isRed(node.left) && isRed(node.left)) {
            flipColors(node);
        }

        return node;
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

    public static RBTree buildTree(List<Integer> list) {
        RBTree<Integer> rbTree = new RBTree<>();

        for (Integer item: list) {
            rbTree.add(item);
        }

        return rbTree;
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

        size --;
    }

    private TreeNode remove (TreeNode root, E e) {
        if (root.left != null && e.compareTo(root.left.data) == 0) {
            if (root.left.left == null && root.left.right == null)
                root.left = null;
            else if (root.left.left != null && root.left.right == null) {
                root.left = root.left.left;
            }
            else if (root.left.left == null && root.left.right != null) {
                root.left = root.left.right;
            }
            else if (root.left.left != null && root.left.right != null) {
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
        }

        else if (e.compareTo(root.right.data) == 0) {
            if (root.right.left == null && root.right.right == null)
                root.right = null;
            else if (root.right.left != null && root.right.right == null) {
                root.right = root.right.left;
            }
            else if (root.right.left == null && root.right.right != null) {
                root.right = root.right.right;
            }
            else if (root.right.left != null && root.right.right != null) {
                TreeNode delNode = root.right;
                TreeNode rMax = findRightMin(delNode.right);
                rMax.left = delNode.left;
                root.right = delNode.right;
            }
        }
        else if (e.compareTo(root.left.data) < 0  || (e.compareTo(root.data) < 0))
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
            throw new IllegalArgumentException("RBTree is empty");

        LinkedQueue<TreeNode> linkedQueue = new LinkedQueue<>();
        linkedQueue.enqueue(root);

        while (!linkedQueue.isEmpty()) {
            TreeNode deNode =  linkedQueue.dequeue();

            if (deNode != null) {
                System.out.println(deNode.data + "\t");

                linkedQueue.enqueue(deNode.left);
                linkedQueue.enqueue(deNode.right);
            }
        }
    }

    public E minimum() {
        if (size ==0)
            throw new IllegalArgumentException("RBTree is empty");
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
        if (size ==0)
            throw new IllegalArgumentException("RBTree is empty");

        root = removeMin(root);
        size --;

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
        if (size ==0)
            throw new IllegalArgumentException("RBTree is empty");
        return maximum(root).data;    }

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
        size --;
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
        size --;
        root = hRemove(root, e);
    }

    private TreeNode hRemove(TreeNode node, E e) {
        if (node == null)
            return null;

        if (e.compareTo(node.data) < 0)
            node.left = hRemove(node.left, e);

        else if (e.compareTo(node.data) > 0) {
            node.right = hRemove(node.right, e);
        }

        else {
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
        RBTree rbTree = RBTree.buildTree(Arrays.asList(11, 6, 4, 8));

        rbTree.preOrder();
        rbTree.hRemove(4);
        System.out.println("hRemove " + 4);
        rbTree.preOrder();

    }
}
