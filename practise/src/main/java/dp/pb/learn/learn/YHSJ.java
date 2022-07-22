package dp.pb.learn.learn;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author pengbo
 * @date 2021/5/24 16:17
 * @name:
 */
public class YHSJ {


    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new LinkedList<>();
        if (numRows <= 0){
            return ret;
        }

        LinkedList<Integer> cur = new LinkedList<Integer>() {{add(1);}};
        ret.add(cur);
        for (int i = 2; i <= numRows; ++i) {
            cur = new LinkedList<>();
            List<Integer> last = ret.get(i - 2);    // 注意下标

            cur.add(last.get(0));
            for (int j = 1; j < i - 1; ++j) {
                cur.add(last.get(j) + last.get(j - 1));
            }
            cur.add(last.get(i - 2));
            ret.add(cur);
        }
        return ret;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }


        Queue<TreeNode> q = new LinkedBlockingDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.peek();
            q.poll();
            ArrayList<Integer> a = new ArrayList<>();
            a.add(node.val);
            if (node.left != null){
                q.offer(node.left);
            }

            if (node.right != null){
                q.offer(node.right);
            }

        }
        return res;
    }


    public static void main(String[] args) {
        int[] kk ={1,2,2,3,4,5,6,7,9,11};
        findSum(kk,5);
    }

    public static void findSum(int[] num, int sum) {
        for (int i = 0; i < num.length; i++) {
            int left = i;
            int right = i+1;
            if(right>num.length-1){
                return;
            }
            int curSum=num[left]+num[right];
            while (curSum < sum) {
                right++;
                curSum += num[right];
            }
            if (curSum == sum) {
                for (int j = left; j <=right; j++) {
                    System.out.print(num[j] + " ");
                }
                System.out.println();
            }
        }
    }
    //@TODO:leet115
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            char sChar = s.charAt(i);
            for (int j = n - 1; j >= 0; j--) {
                char tChar = t.charAt(j);
                if (sChar == tChar) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }

}
