package dp.pb.learn.learn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author pengbo
 * @date 2022/2/23 9:23
 * @name:
 */
public class HasStatic {

    private static int x=100;
//     public static void main(String args[]){
//                 HasStatic hs1=new HasStatic();
//                 hs1.x++;
//                HasStatic  hs2=new HasStatic();
//                  hs2.x++;
//                  hs1=new HasStatic();
//               hs1.x++;
//                HasStatic.x--;
//                System.out.println("x="+x);
//            }


    public  static int findJudge(int n, int[][] trust) {
        int ans =-1;
        Map<Integer,Integer> t =new HashMap<>();
        Set<Integer> no =new HashSet<>();
        for(int[] l :trust){
            int k =t.getOrDefault(l[1],0);
            k++;
            t.put(l[1],k);
            no.add(l[0]);
        }

        for(Map.Entry<Integer,Integer> e :t.entrySet()){
            if(e.getValue()==n-1&&!no.contains(e.getKey())){
                return e.getKey();
            }

        }
        return ans;
    }

    public static void main(String[] args) {
         int[][] k ={{1,3},{2,3},{3,1}};
        int sss= findJudge(3, k);
        System.err.println(sss);
    }
}
