package com.solution;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * @author lxq
 * @date 2021年06月08日 9:14
 */
public class 二叉树中的最大路径和 {

    public static int aloneSum = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode node1  = new TreeNode(9);
        TreeNode node2 = new TreeNode(15);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(20, node2, node3);
        TreeNode root = new TreeNode(-10, node1, node4);
        System.out.println(maxPathSum(root));
    }


    /**
     所有树的题目，都想成一颗只有根、左节点、右节点 的小树。然后一颗颗小树构成整棵大树，所以只需要考虑这颗小树即可。接下来分情况，
     按照题意：一颗三个节点的小树的结果只可能有如下6种情况：
     根 + 左 + 右
     根 + 左
     根 + 右
     根
     左
     右
     好了，分析上述6种情况， 只有 2,3,4 可以向上累加，而1,5,6不可以累加
     （这个很好想，情况1向上累加的话，必然出现分叉，情况5和6直接就跟上面的树枝断开的，没法累加），
     所以我们找一个全局变量存储 1,5,6这三种不可累加的最大值， 另一方面咱们用遍历树的方法求2,3,4这三种可以累加的情况。
     最后把两类情况得到的最大值再取一个最大值即可。
     * @author lxq
     * @date 2021/6/8 9:46
     * @param root
     * @return int
     */
    public static int maxPathSum(TreeNode root) {
        int pathSum =  scan(root);
        return Math.max(aloneSum, pathSum);
    }

    private static int scan(TreeNode root) {
        if (root == null){
            return Integer.MIN_VALUE;
        }
        int left = scan(root.left);
        int right = scan(root.right);
        aloneSum = max(aloneSum, root.val + left + right, left, right);
        return max(root.val, root.val + left, root.val + right);
    }

    public static int max(int... var0) {
        int var1 = -2147483648;
        int[] var2 = var0;
        int var3 = var0.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            int var5 = var2[var4];
            if (var5 > var1) {
                var1 = var5;
            }
        }

        return var1;
    }
}




