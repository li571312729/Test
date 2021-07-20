package com.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxq
 * @date 2021年07月13日 15:14
 */
public class 二叉树根节点到叶子节点和为指定值的路径 {
    public static void main(String[] args) {

        TreeNode root3 = new TreeNode(2);
        TreeNode root4 = new TreeNode(7);
        TreeNode root5 = new TreeNode(1);
        TreeNode root6 = new TreeNode(11, root3, root4);
        TreeNode root7 = new TreeNode(9);
        TreeNode root1 = new TreeNode(4, root5, root6);
        TreeNode root2 = new TreeNode(8, null, root7);
        TreeNode root = new TreeNode(5, root1, root2);
        ArrayList<ArrayList<Integer>> arrayLists = pathSum(root, 22);
        System.out.println(111111);
    }

    /**
     * 给定一个二叉树和一个值 sum，请找出所有的根节点到叶子节点的节点值之和等于sum 的路径，
     * 例如：
     * 给出如下的二叉树，
     * @author lxq
     * @date 2021/7/13 15:14
     * @param root
     * @param sum
     * @return java.util.ArrayList<java.util.ArrayList<java.lang.Integer>>
     */
    static ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    static ArrayList<Integer> ls = new ArrayList<>();
    static int count = 0;

    public static ArrayList<ArrayList<Integer>> pathSum (TreeNode root, int sum) {
        dfs(root, sum);
        return ans;
    }

    public static void dfs(TreeNode root, int sum)
    {
        if(root == null){
            return;
        }

        ls.add(root.val);
        count = count + root.val;

        if(root.left == null && root.right == null && count == sum){
            ans.add(new ArrayList<>(ls));
        }

        dfs(root.left, sum);
        dfs(root.right, sum);
        ls.remove(ls.size() - 1);
        count = count - root.val;
    }

}
