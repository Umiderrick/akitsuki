package dp.pb.learn.learn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author pengbo
 * @date 2021/6/25 16:55
 * @name:
 */
class Salu {


    static List<List<Integer>> res =new ArrayList<>();
    public static List<List<Integer>> combine(int n, int k) {
        if (k <= 0 || n <= 0) return res;
        Stack<Integer> track =new Stack<>();
        backtrack(n, k, 1, track);
        return res;
    }
    static void backtrack(int n, int k, int start, Stack<Integer> track) {
        // 到达树的底部
        if (k == track.size()) {
            res.add(new ArrayList<>(track));
            return;
        }
        // 注意 i 从 start 开始递增
        for (int i = start; i <= n; i++) {
            // 做选择
            track.push(i);
            backtrack(n, k, i + 1, track);
            // 撤销选择
            track.pop();
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> combine = combine(4, 2);

    }
}
