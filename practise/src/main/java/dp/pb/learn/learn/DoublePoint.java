package dp.pb.learn.learn;

/**
 * @author pengbo
 * @date 2021/12/20 16:26
 * @name:
 */
public class DoublePoint {

    boolean check(int[] houses, int[] heaters, int x) {
        int n = houses.length, m = heaters.length;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < m && houses[i] > heaters[j] + x){
                j++;
            }
            if (j < m && heaters[j] - x <= houses[i] && houses[i] <= heaters[j] + x) {
                continue;
            }
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        int[] a ={1,2,3,4};
        int[] b ={1,4};
        DoublePoint d =new DoublePoint();
        d.check(a, b, Integer.MAX_VALUE / 2);

    }

}
