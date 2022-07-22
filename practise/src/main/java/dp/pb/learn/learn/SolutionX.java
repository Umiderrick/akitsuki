package dp.pb.learn.learn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengbo
 * @date 2021/5/26 17:20
 * @name: 11
 */
public class SolutionX {

    // 维护当前的路径
    private static List<Integer> path =new ArrayList<>();
    // 结果
    private static List<List<Integer>> res=new ArrayList<>();
    public static List<List<Integer>> pathSum(TreeNode root, int target) {
        dfs(root, target);
        return res;
    }

    // 递归求解
    private static void dfs(TreeNode curr, int target)
    {
        // 忽略空结点
        if (curr != null)
        {
            target -= curr.val;
            path.add(curr.val);
            if (target != 0 || curr.left != null || curr.right != null)
            {
                dfs(curr.left, target);
                dfs(curr.right, target);
            }
            else
            {
                // 满足结果且为根节点
                ArrayList<Integer> over = new ArrayList<>(path);
                res.add(over);
            }
            // 回溯
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        TreeNode root =new TreeNode(5);
        TreeNode root1 =new TreeNode(4);
        TreeNode root2 =new TreeNode(8);
        TreeNode root3 =new TreeNode(11);
        TreeNode root4 =new TreeNode(13);

        TreeNode root5 =new TreeNode(4);
        TreeNode root6 =new TreeNode(7);
        TreeNode root7 =new TreeNode(2);
        TreeNode root8 =new TreeNode(5);
        TreeNode root9 =new TreeNode(1);


        root.left=root1;
        root.right=root2;
        root1.left=root3;
        root2.left=root4;
        root2.right=root5;
        root3.left=root6;
        root3.right=root7;
        root5.left=root8;
        root5.right=root9;
        List<List<Integer>> lists = pathSum(root, 22);
        System.err.println(lists);
    }
}
