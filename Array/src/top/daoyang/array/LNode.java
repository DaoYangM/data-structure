package top.daoyang.array;

import javax.xml.soap.Node;
import java.util.Arrays;
import java.util.List;

public class LNode<E> {
    public E data;

    public LNode next;

    public LNode(E data, LNode next) {
        this.data = data;
        this.next = next;
    }

    public LNode(E data) {
        this(data, null);
    }

    public LNode() {
        this(null, null);
    }

    public LNode(List<E> lists) {

        if (lists == null || lists.size() == 0)
            throw new IllegalArgumentException("lists == null || lists.size() == 0");

        this.data = lists.get(0);
        LNode loopNode = this;

        for (E item : lists.subList(1, lists.size())) {
            LNode<E> node = new LNode<>(item);
            loopNode.next = node;
            loopNode = node;
        }
    }

    public static LNode generate(List<Integer> lists) {

        if (lists == null || lists.size() == 0)
            throw new IllegalArgumentException("lists == null || lists.size() == 0");

        LNode head = null, loopLNode = null;

        for (Integer item : lists) {

            if (head == null) {
                LNode<Integer> node = new LNode<>(item);
                head = node;
                loopLNode = head;
            } else {
                LNode<Integer> node = new LNode<>(item);
                loopLNode.next = node;
                loopLNode = node;
            }
        }

        return head;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        LNode loopNode = this;

        while (loopNode != null) {

            stringBuilder.append(loopNode.data);
            loopNode = loopNode.next;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
//        LNode head = LNode.generate(Arrays.asList(1, 1, 2, 3, 1));

        LNode<Integer> head = new LNode(Arrays.asList(1, 1, 2, 3, 1));

        while (head != null) {
            System.out.print("del: " + head.data);
            head = head.next;
        }
    }
}
