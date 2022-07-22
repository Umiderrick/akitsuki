package dp.pb.learn.learn;

/**
 * @author pengbo
 * @date 2021/7/21 8:57
 * @name:
 */
public class SolutionXX {

    public int preimageSizeFZF(int k) {
// 左边界和右边界之差 + 1 就是答案
        return (int)(right_bound(k) - left_bound(k)+ 1);
    }

    public static void main(String[] args) {
        SolutionXX x =new SolutionXX();
        int i = x.preimageSizeFZF(38995104);
        System.err.println(i);
    }

    /* 搜索 trailingZeroes(n) == K 的左侧边界 */
    long left_bound(int target) {
        long lo = 0, hi = 100000000;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (trailingZeroes(mid) < target) {
                lo = mid + 1;
            } else if (trailingZeroes(mid) > target) {
                hi = mid;
            } else {
                hi = mid;
            }
        }

        return lo;
    }
    // 逻辑不变，数据类型全部改成 long
    long trailingZeroes(long n) {
        long res = 0;
        for (long d = n; d / 5 > 0; d = d / 5) {
            res += d / 5;
        }
        return res;
    }

    /* 搜索 trailingZeroes(n) == K 的右侧边界 */
    long right_bound(int target) {
        long lo = 0, hi = 100000000;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (trailingZeroes(mid) < target) {
                lo = mid + 1;
            } else if (trailingZeroes(mid) > target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo - 1;
    }
}
