import java.util.*;

public class Solution {

    private class Freq implements Comparable<Freq> {
        private int key;

        private int value;

        @Override
        public int compareTo(Freq o) {
            if (this.value < o.value)
                return 1;
            else if (this.value > o.value)
                return -1;
            else
                return 0;
        }

        public Freq(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "" + key;
        }
    }

    public List<Integer> topFrequent(int[] nums, int k) {
        Map<Integer, Integer> treeMap = new TreeMap<>();

        for (int num :nums) {
            if (treeMap.containsKey(num))
                treeMap.put(num, treeMap.get(num) + 1);
            else
                treeMap.put(num, 1);
        }

        PriorityQueue<Freq> freqPriorityQueue = new PriorityQueue<>();

        for (Map.Entry<Integer, Integer> entry: treeMap.entrySet()) {
            if (freqPriorityQueue.getSize() < k) {
                freqPriorityQueue.enqueue(new Freq(entry.getKey(), entry.getValue()));
            } else {
                if (entry.getValue() > freqPriorityQueue.getFront().value) {
                    freqPriorityQueue.dequeue();
                    freqPriorityQueue.enqueue(new Freq(entry.getKey(), entry.getValue()));
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!freqPriorityQueue.isEmpty())
            result.add(freqPriorityQueue.dequeue().key);


        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.topFrequent(new int[]{1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5}, 3));

    }
}
