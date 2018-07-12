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

    public TreeNode remove (TreeNode root, E e) {
        if (e.compareTo(root.left.data) == 0) {
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

    private TreeNode findRightMin(TreeNode root) {
        if (root.left != null)
            return findRightMin(root.left);
        return root;
    }

    public static void main(String[] args) {
        BST<Integer> bst = BST.buildTree(Arrays.asList(11, 5, 2, 1, 4, 3, 5, 8, 7, 9, 22, 19, 15, 12 ,16, 17, 23, 20));
//        BST<Integer> bst = BST.buildTree(Arrays.asList(11, 10, 13, 12, 24, 15, 25));

        bst.remove(2);
//        System.out.println(bst.findRightMax(bst.root.left.left));
     }
}
