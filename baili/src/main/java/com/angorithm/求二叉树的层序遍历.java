package com.angorithm;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author lxq
 * @date 2021年06月11日 16:09
 */
public class 求二叉树的层序遍历 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        TreeNode root4 = new TreeNode(4);
        TreeNode root5 = new TreeNode(5);
        root.left = root2;
        root.right = root3;
        root3.left = root4;
        root3.right = root5;
        ArrayList<ArrayList<Integer>> arrayLists = levelOrder1(root);
        System.out.println(111111);
    }

    public static ArrayList<ArrayList<Integer>> levelOrder1(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>(10);
        if(root == null){
            return res;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            ArrayList<Integer> temp = new ArrayList<>(10);
            int size = queue.size();
            while (size-- > 0){
                TreeNode poll = queue.poll();
                temp.add(poll.val);
                if(poll.left != null){
                    queue.offer(poll.left);
                }

                if(poll.right != null){
                    queue.offer(poll.right);
                }
            }
            res.add(temp);
        }
        return res;
    }

    public static void print(TreeNode root) {
        if(root != null){
            System.out.println(root.val);
            levelOrder1(root.left);
            levelOrder1(root.right);
        }
    }

    /**
     *
     * @param root TreeNode类
     * @return int整型ArrayList<ArrayList<>>
     */
    public static ArrayList<ArrayList<Integer>> levelOrder (TreeNode root) {
        // write code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(list);
        }
        return result;
    }
}
