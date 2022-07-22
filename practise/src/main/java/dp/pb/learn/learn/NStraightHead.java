package dp.pb.learn.learn;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author pengbo
 * @date 2021/12/30 14:17
 * @name:
 */
public class NStraightHead {

    public static boolean isNStraightHand(int[] hand, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i : hand) {
            int k =map.getOrDefault(i, 0);
            map.put(i, ++k);
            q.add(i);
        }
        while (!q.isEmpty()) {
            int t = q.poll();
            if (map.get(t) == 0) continue;
            for (int i = 0; i < m; i++) {
                int cnt = map.getOrDefault(t + i, 0);
                if (cnt == 0) return false;
                map.put(t + i, cnt - 1);
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        int[] k ={1,2,3,3,4,5,8,9,6,10};
        isNStraightHand(k, 3);
    }
}
