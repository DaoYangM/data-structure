import top.daoyang.array.LNode;

import java.util.Arrays;
import java.util.List;

public class RSUM {

    public static int rSUM(List<Integer> c) {

        if (c.size() == 1) {
            return c.get(0);
        }
        int r = rSUM(c.subList(1, c.size()));
        r = r + c.get(0);

        return r;
    }

    public static LNode removeAllTheSame(LNode head, int val) {
        if (head != null) {
            LNode rNode = removeAllTheSame(head.next, val);
            if ((Integer) head.data == val) {
                return rNode;
            } else {
                head.next = rNode;
                return head;
            }
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
//        System.out.println(rSUM(Arrays.asList(1, 2, 3, 4, 5)));

        LNode head = LNode.generate(Arrays.asList(1, 2, 1, 3, 4, 5));
        System.out.println(head);

        System.out.println(removeAllTheSame(head, 1));
    }
}
