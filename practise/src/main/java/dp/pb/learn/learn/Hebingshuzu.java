package dp.pb.learn.learn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author pengbo
 * @date 2021/5/21 11:24
 * @name:
 */
public class Hebingshuzu {

    public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map =new HashMap<>();
        for(int i = 0 ; i < nums1.length ; i ++){
            Integer orDefault = map.getOrDefault(nums1[i], 0);
            map.put(nums1[i],++orDefault);
        }
        /**
         * vector<int> resultVector;
         *         for(int i = 0 ; i < nums2.size() ; i ++){
         *
         *         }
         */
        Set<Integer> r = new HashSet<>();
        for(int j=0;j<nums2.length;j++){
            if(map.get(nums2[j]) > 0){
                r.add(nums2[j]);
                Integer integer = map.get(nums2[j]);
                integer--;
                map.put(nums2[j],integer);
            }
        }
        int[] ints = new int[r.size()];
        int i=0;
        Iterator<Integer> iterator = r.iterator();
        while (iterator.hasNext()){
            ints[i] =iterator.next();
            i++;
        }
        return ints;
    }

    public static void main(String[] args) {
        int[] a={1,2,2,4};
        int[] b={2,2};
        int[] intersect = intersect(a, b);
        System.err.println(intersect);

    }
}
