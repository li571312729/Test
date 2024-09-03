package com.angorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 *
 * 树的 最大宽度 是所有层中最大的 宽度 。
 *
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
 *
 * 题目数据保证答案将会在  32 位 带符号整数范围内。
 *
 * 输入：root = [1,3,2,5,null,null,9,6,null,7]
 * 输出：7
 * 解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
 *
 */
public class 二叉树最大宽度662 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode root1 = new TreeNode(2);
        TreeNode root2 = new TreeNode(1);
        TreeNode root3 = new TreeNode(3);
        root.left = root1;
        root1.left = root2;
        root1.right = root3;
        System.out.println(widthOfBinaryTree(root));
    }

    public static int widthOfBinaryTree(TreeNode root) {
        int maxCount = 0;
        if(root == null){
            return maxCount;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 如果根不为空则树的保底宽度为根这一层的宽度1
        maxCount = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            List<TreeNode> list = new ArrayList<>();
            int count;
            while (size > 0){
                TreeNode node = queue.poll();

                if(!(list.isEmpty() && node == null)){
                    list.add(node);
                    if(node != null && list.size() > 1){
                        count = list.size();
                        maxCount = Math.max(maxCount, count);
                    }
                }
                queue.add(node == null ? null : node.left);
                queue.add(node == null ? null : node.right);
                size --;
            }

            //如果某一层全是null，则表示树的遍历结束了
            if(list.isEmpty()){
                break;
            }
        }
        return maxCount;
    }

}





