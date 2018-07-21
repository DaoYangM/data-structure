import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;

public class Trie {

    private Node root;

    private int size;

    private class Node {
        private boolean isWord;

        private Map<Character, Node> next;

        private Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }

        private Node() {
            this(false);
        }
    }

    public Trie() {
        root = new Node();

        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void add(String word) {

        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char w = word.charAt(i);

            if (!cur.next.containsKey(w))
                cur.next.put(w, new Node());

            cur = cur.next.get(w);
        }

        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    public void rAdd(String word) {
        rAdd(word, 0, root);
    }

    public Node rAdd(String word, int i, Node node) {

        char w = word.charAt(i);
        if (!node.next.containsKey(w))
            node.next.put(w, new Node());

        if (i == word.length() - 1) {
            Node cur = node.next.get(w);
            cur.isWord = true;
            size++;

            return cur;
        }

        i += 1;
        return rAdd(word, i, node.next.get(w));
    }

    public boolean contains(String word) {
        return contains(word, node -> node.isWord);
    }

    public boolean isPrefix(String word) {
        return contains(word, node -> true);
    }

    private boolean contains(String word, Predicate<Node> predicate) {

        if (word.trim().equals(""))
            throw new IllegalArgumentException("word is null");

        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char w = word.charAt(i);

            if (!cur.next.containsKey(w))
                return false;

            cur = cur.next.get(w);
        }

        return predicate.test(cur);
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.rAdd("he");
        trie.rAdd("hello");

        System.out.println(trie.isPrefix("hello"));
    }
}
