package xsy.forstudying.practice.algorithm;

import java.util.*;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2021-12-30 16:46
 **/
public class Solution1 {
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        int length = hand.length;
        if (length % groupSize != 0) {
            return false;
        }
        Arrays.sort(hand);
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : hand) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        for (int b : hand) {
            if (!map.containsKey(b)) {
                continue;
            }
            for (int i = 0; i < groupSize; i++) {
                int nextNum = b + i;
                if (!map.containsKey(nextNum)) {
                    return false;
                }
                map.put(nextNum, map.get(nextNum) - 1);
                if (map.get(nextNum) == 0) {
                    map.remove(nextNum);
                }
            }
        }
        return true;
    }

    public static boolean isNStraightHand2(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }
        if (groupSize == 1) {
            return true;
        }
        Arrays.sort(hand);
        int result = 0;
        Queue<List<Integer>> queue = new LinkedList<>();
        for (int i = 0; i < hand.length; i++) {
            boolean find = false;
            for (List<Integer> list : queue) {
                if (list.get(list.size() - 1) + 1 == hand[i]) {
                    find = true;
                    list.add(hand[i]);
                    if (list.size() == groupSize) {
                        queue.remove(list);
                    }
                    break;
                }
            }
            if (!find) {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(hand[i]);
                queue.add(list);
                result++;
                if (result > hand.length / groupSize) {
                    return false;
                }
                if (list.size() == groupSize) {
                    queue.remove(list);
                }
            }
        }
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        int[] arrs = new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;
        System.out.println(isNStraightHand2(arrs, groupSize));
    }
}
