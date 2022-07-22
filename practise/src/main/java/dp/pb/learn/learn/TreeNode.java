package dp.pb.learn.learn;

import java.util.*;

/**
 * @author pengbo
 * @date 2022/3/4 18:27
 * @name:
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;


    public TreeNode(int v){
        this.val =v;
    }

    public List<Integer> preOrderIteration(TreeNode head) {
        if (head == null) {
            return new ArrayList<>();
        }
        // 结果集
        List<Integer> resultList = new ArrayList<>();
        // 栈
        Stack<TreeNode> stack = new Stack<>();
        // 首先放入头节点
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            resultList.add(node.val);
            // 放入右节点
            if (node.right != null) {
                stack.push(node.right);
            }
            // 放入左节点
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return resultList;
    }
    public List<Integer> inOrderIteration(TreeNode head) {

        if (head == null) {
            return new ArrayList<>();
        }
        // 结果集
        List<Integer> resultList = new ArrayList<>();
        TreeNode cur = head;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            // 一直先把左节点依次放入栈中
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            resultList.add(node.val);
            if (node.right != null) {
                cur = node.right;
            }
        }
        return resultList;
    }
    public List<Integer> postOrderIteration(TreeNode head) {
        if (head == null) {
            return new ArrayList();
        }
        List<Integer> resultList=  new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            resultList.add(stack2.pop().val);
        }
        return  resultList;
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 返回的结果集
        List<List<Integer>> res = new ArrayList<>();
        // 队列
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        Map<TreeNode,Integer> m =new HashMap<>();
        m.put(root,1);
        int l =1;
        int xx =0;
        int max =Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                int cl =m.get(poll);
                if(cl ==l){
                    xx++;
                }else {
                    max=Math.max(max,xx);
                    l++;
                    xx=1;
                }
                temp.add(poll.val);
                if (poll.left != null) {
                    m.put(poll.left,cl+1);
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    m.put(poll.right,cl+1);
                    queue.add(poll.right);
                }
            }
            res.add(temp);
        }
        System.err.println(max);
        return res;
    }

    public static void main(String[] args) {
        int k =127;
        while(k!=1){
            k>>>=1;
            System.err.println(Integer.toBinaryString(k));
        }
        int k2 =127;
        while(k2!=1){
            k2>>=1;
            System.err.println(Integer.toBinaryString(k2));
        }
        TreeMap<String,Integer> m =new TreeMap<>();

        int k3 =126;
        while(k3!=Integer.MIN_VALUE){
            k3<<=1;
            System.err.println(Integer.toBinaryString(k3));
        }
    }


}
