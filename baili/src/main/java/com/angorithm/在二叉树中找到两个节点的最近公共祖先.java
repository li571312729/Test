package com.angorithm;

/**
 * @author lxq
 * @date 2021年06月28日 10:56
 */
public class 在二叉树中找到两个节点的最近公共祖先 {

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode() {

        }
    }

    /**
     * [3,5,1,6,2,0,8,#,#,7,4],5,1
     * @author lxq
     * @date 2021/6/28 10:58
     * @param args
     */
    public static void main(String[] args) {

        TreeNode root = new TreeNode(3);
        TreeNode o = new TreeNode(5);
        TreeNode o1 = new TreeNode(1);
        TreeNode o2 = new TreeNode(6);
        TreeNode o3 = new TreeNode(2);
        TreeNode o4 = new TreeNode(0);
        TreeNode o5 = new TreeNode(8);
        TreeNode o6 = new TreeNode(7);
        TreeNode o7 = new TreeNode(4);
        root.left = o;
        root.right = o1;
        o.left = o2;
        o.right = o3;
        o1.left = o4;
        o1.right = o5;
        o3.left = o6;
        o3.right = o7;
        int i = lowestCommonAncestor(root, 6, 7);
        System.out.println(i);
    }

    /**
     * 给定一棵二叉树(保证非空)以及这棵树上的两个节点对应的val值 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
     * 最近公共祖先和o1,o2有三种关系：
     *
     * o1,o2分别在祖先左右两侧
     * 祖先是o1，o2在祖先左/右侧
     * 祖先是o2，o1在祖先左/右侧
     * 使用深度优先 Depth first search  dfs深度遍历，如果节点为o1,o2中其中一个直接返回，如果节点超过叶子节点也返回
     *
     * @param root TreeNode类
     * @param o1 int整型
     * @param o2 int整型
     * @return int整型
     */
    public static int lowestCommonAncestor (TreeNode root, int o1, int o2) {
        return CommonAncestor(root, o1, o2).val;
    }

    public static TreeNode CommonAncestor (TreeNode root, int o1, int o2) {
        if (root == null || root.val == o1 || root.val == o2) { // 超过叶子节点，或者root为p、q中的一个直接返回
            return root;
        }
        TreeNode left = CommonAncestor(root.left,o1,o2); // 返回左侧的p\q节点
        TreeNode right = CommonAncestor(root.right,o1,o2); // 返回右侧的p\q节点
        if (left == null) {  // 都在右侧
            return right;
        }
        if (right == null) { // 都在左侧
            return left;
        }
        return root; // 在左右两侧
    }



}


