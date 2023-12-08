package com.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 *
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 *
 * 输入：[1,1,1,1,1,null,1]
 * 输出：true
 */
public class 单值二叉树965 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(1);
        TreeNode root3 = new TreeNode(2);
        root.left = root1;
        root1.left = root2;
        root1.right = root3;
        System.out.println(isUnivalTree(root));
    }


    public static boolean isUnivalTree(TreeNode root) {
        if(root == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if(node.left!= null){
                    queue.offer(node.left);
                    if(root.val!= node.left.val){
                        return false;
                    }
                }
                if(node.right!= null){
                    queue.offer(node.right);
                    if(root.val!= node.right.val){
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
