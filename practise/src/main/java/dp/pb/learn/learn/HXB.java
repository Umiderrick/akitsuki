package dp.pb.learn.learn;

import dp.pb.test.ListNode;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author pengbo
 * @date 2021/5/21 8:58
 * @name:
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，
 * 其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 *
 * 返回平面上所有回旋镖的数量。
 *
 *  
 * 示例 1：
 *
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * 示例 2：
 *
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：2
 * 示例 3：
 *
 * 输入：points = [[1,1]]
 * 输出：0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-boomerangs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HXB {

    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                int len = (points[j][0] - points[i][0]) * (points[j][0] - points[i][0])
                        + (points[j][1] - points[i][1]) * (points[j][1] - points[i][1]);
                Integer count = map.getOrDefault(len, 0);
                map.put(len, count + 1);
            }
            for (Integer value : map.values()) {
                res += value * (value - 1);
            }
        }
        return res;
    }



    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // # 分别入栈
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        while (l1.next!=null){
            stack1.push(l1.val);
            l1 = l1.next;
        }
        stack1.push(l1.val);
        while (l2.next!=null){
            stack2.push(l2.val);
            l2 = l2.next;
        }
        stack2.push(l2.val);
        int flag = 0;
        ListNode head =null;
        while (!stack1.isEmpty() ||!stack2.isEmpty() ||flag != 0){
            flag += stack1.pop();
            flag += stack2.pop();
            ListNode node =new ListNode(flag % 10);
            node.next = head;
            head = node;
            flag = flag/ 10;

        }

        return head;
    }

    public static void main(String[] args) {
        ListNode a =new ListNode(1);
        ListNode b =new ListNode(2);
        a.next =b;
        ListNode c =new ListNode(3);
        b.next =c;
        ListNode d =new ListNode(1);
        ListNode e =new ListNode(2);
        d.next =e;
        ListNode f =new ListNode(3);
        e.next =f;

        ListNode listNode = addTwoNumbers(a, d);
        while (listNode.next!=null){
            System.err.println(listNode.val);
            listNode =listNode.next;
        }
        System.err.println(listNode.val);

    }
}
