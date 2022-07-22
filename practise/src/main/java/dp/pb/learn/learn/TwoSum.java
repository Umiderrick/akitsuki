package dp.pb.learn.learn;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pengbo
 * @date 2022/7/20 18:53
 * @name:
 */
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int result[] = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            if (map.containsKey(num)) {
                result[0] = map.get(num);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }

}
