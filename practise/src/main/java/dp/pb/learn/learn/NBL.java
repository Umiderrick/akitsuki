package dp.pb.learn.learn;

import java.util.Stack;

/**
 * @author pengbo
 * @date 2021/5/24 15:30
 * @name:
 */
public class NBL {

    public int evalRPN(String[] tokens) {
        Stack<Integer> nums= new Stack<>();
        for(String s: tokens){
            if(s.equals( "+") || s.equals("-") || s.equals("*") || s.equals("/")){
                int a = nums.peek();
                nums.pop();
                int b = nums.peek();
                nums.pop();

                if(s .equals( "+")){
                    nums.push(b + a);
                }else if(s.equals( "-")){
                    nums.push(b - a);
                } else if(s.equals("*")){
                    nums.push(b * a);
                }else if(s.equals("/")){
                    nums.push(b / a);
                }
            }
            else{
                nums.push(Integer.parseInt(s));
            }
        }
        return nums.peek();
    }
}
