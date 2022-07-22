package dp.pb.learn.learn;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author pengbo
 * @date 2022/1/21 16:04
 * @name:
 */
public class CanRich {
    boolean canReach(int[] arr, int start) {
        if (arr[start] == 0) {
            return true;
        }

        int n = arr.length;
        boolean[] used=new boolean[n];
        Queue<Integer> q=new ArrayDeque<>();
        q.add(start);
        used[start] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u + arr[u] < n && !used[u + arr[u]]) {
                if (arr[u + arr[u]] == 0) {
                    return true;
                }
                q.add(u + arr[u]);
                used[u + arr[u]] = true;
            }
            if (u - arr[u] >= 0 && !used[u - arr[u]]) {
                if (arr[u - arr[u]] == 0) {
                    return true;
                }
                q.add(u - arr[u]);
                used[u - arr[u]] = true;
            }
        }
        return false;
    }
}
