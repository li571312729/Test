package com.solution;

import java.util.Objects;

/**
 * @author xiaoqiangli
 * @Date 2021-11-23
 */
public class 验证二叉搜索树 {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(5);
        TreeNode l = new TreeNode(4);
        TreeNode r = new TreeNode(6);
        TreeNode r1 = new TreeNode(3);
        TreeNode r2 = new TreeNode(7);
        root.right = r;
        root.left = l;
        r.left = r1;
        r.right = r2;
        System.out.println(isValidBST(root));

    }


    /**
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     *
     * 有效 二叉搜索树定义如下：
     *
     *    节点的左子树只包含 小于 当前节点的数。
     *    节点的右子树只包含 大于 当前节点的数。
     *    所有左子树和右子树自身必须也是二叉搜索树。
     *
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        // 中序遍历的递归写法

        if(Objects.isNull(root)){
            return true;
        }

        boolean l = true;
        boolean r = true;

        if(Objects.nonNull(root.left)){
            if(root.left.val < root.val){
                l = isValidBST(root.left);
            }else {
                return false;
            }
        }

        if(Objects.nonNull(root.right)){
            if(root.right.val > root.val){
                r = isValidBST(root.right);
            }else {
                return false;
            }
        }

        return l & r;
    }
}
