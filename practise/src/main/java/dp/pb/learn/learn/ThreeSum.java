package dp.pb.learn.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author pengbo
 * @date 2021/5/24 18:35
 * @name:
 */
public class ThreeSum {


    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(nums);
        if (nums.length==0) return res;
        for (int k = 0; k < nums.length; ++k) {
            if (nums[k] > 0) break;
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            int target = 0 - nums[k];
            int i = k + 1, j = nums.length - 1;
            while (i < j) {
                if (nums[i] + nums[j] == target) {
                    res.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    while (i < j && nums[i] == nums[i + 1]) ++i;
                    while (i < j && nums[j] == nums[j - 1]) --j;
                    ++i; --j;
                } else if (nums[i] + nums[j] < target) ++i;
                else --j;
            }
        }
        return res;
    }
    public int myAtoi(String s) {
        String str =s.trim();
        if (str.length() == 0){
            return 0;
        }

        boolean isNegative = str.charAt(0) == '-';
        int ret = 0;
        char cc = str.charAt(0);

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && (c == '+' || c == '-'))  /* 符号判定 */
                continue;
            if (c < '0' || c > '9'){
                continue;
            }                /* 非法输入 */

            ret = ret * 10 + (c - '0');
        }
        return isNegative ? -ret : ret;
    }

    public static int search(int[] nums, int target) {
        int len =nums.length;
        int l =0;
        int r =len-1;

        while(l<=r){
            int mid =(l+r+1)>>>1;
            if(nums[mid]>target){
                r=mid;
            }else{
                l=mid;
            }
            if(nums[l]==target){
                return l;
            }

        }
        return -1;
    }

    public static int cuttingRope(int n) {
        if ( n <= 1 ) {
            return 1;
        }
        long[] dp = new long[n + 1];
        dp[2] = 1;
        for(int i = 3; i < n + 1; i++){
            for(int j = 2; j < i; j++){
                dp[i] = Math.max(dp[i], Math.max((long) j * (i - j), j * dp[i - j]));
            }
        }
        double pow = Math.pow(10, 9);
        return (int)(dp[n]%(1000000007));
    }


    public static void main(String[] args) {
        int x[] = {-1, 0, 8, 19, 22, 99};
        double pow = Math.pow(10, 4);
//        search(x, 19)
//    }
        System.err.println(pow);
    }
}
