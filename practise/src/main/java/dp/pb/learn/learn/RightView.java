package dp.pb.learn.learn;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author pengbo
 * @date 2021/5/24 14:55
 * @name:
 */
public class RightView {
        public static List<Integer> rightSideView(TreeNode root) {
            List<Integer> res =new ArrayList<>();
            if (root==null) {
                return res;
            }
            Queue<TreeNode> q =new LinkedBlockingDeque<>();
            q.offer(root);
            while (!q.isEmpty()) {
                res.add(q.peek().val);
                int size = q.size();
                for (int i = 0; i < size; ++i) {
                    TreeNode node = q.peek();
                    q.poll();

                    if (node.right!=null){
                        q.offer(node.right);
                    }
                    if (node.left!=null) {
                        q.offer(node.left);
                    }
                }
            }
            return res;
        }

    public static void main(String[] args) {
        TreeNode treeNode =new TreeNode(1);
        
        TreeNode treeNode2 =new TreeNode(2);
        TreeNode treeNode3 =new TreeNode(3);
        TreeNode treeNode4 =new TreeNode(4);
        TreeNode treeNode5 =new TreeNode(5);
        TreeNode treeNode6 =new TreeNode(6);
        treeNode.left=treeNode2;
        treeNode.right=treeNode3;
        treeNode2.right=treeNode4;
        treeNode3.left =treeNode5;
        treeNode3.right =treeNode6;
        List<Integer> integers = rightSideView(treeNode);

    }
}
