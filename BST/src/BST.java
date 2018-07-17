import top.daoyang.queue.LinkedQueue;
import top.daoyang.stack.LinkedStack;

import java.util.Arrays;
import java.util.List;

public class BST<E extends Comparable<E>> {

    private TreeNode root;

    private int size;

    private boolean isDeleteRoot = false;

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

    private enum Flag {
        LNRH,
        LHRN,
        LHRH,
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

    public static BST buildTree(List<Integer> list) {
        BST<Integer> bst = new BST<>();

        for (Integer item: list) {
            bst.add2(item);
        }

        return bst;
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
            throw new IllegalArgumentException("BST is empty");

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
            throw new IllegalArgumentException("BST is empty");
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
            throw new IllegalArgumentException("BST is empty");

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
            throw new IllegalArgumentException("BST is empty");
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
        BST bst = BST.buildTree(Arrays.asList(11, 6, 4, 8));

        bst.preOrder();
        bst.hRemove(4);
        System.out.println("hRemove " + 4);
        bst.preOrder();

        int a = 10;

        if (a == 10)
            System.out.println("a=0");
        else if (a / 2 == 5 ) {
            System.out.println("a/2=5");
        }
     }
}
