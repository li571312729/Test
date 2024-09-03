package com.angorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxq
 * @date 2021年07月02日 14:10
 */
public class 实现二叉树先序中序和后序遍历 {

    public static void main(String[] args) {
        实现二叉树先序中序和后序遍历 a = new 实现二叉树先序中序和后序遍历();



        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root = new TreeNode(0, root1, root2);

        int[][] ints = a.threeOrders(root);

        int[][] ints1 = a.threeOrders1(root);
        System.out.println(111111);

    }

    /**
     *
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    public int flag0 = 0, flag1= 0, flag2 = 0;
    public int[][] threeOrders1 (TreeNode root) {
        //声明返回数组：这里用一个函数来获取树的size
        int[][] arr = new int[3][getTreeSize(root)];
        //递归遍历：（三种递归在一个函数里就可以了）
        travel(root, arr);
        return arr; //返回答案
    }
    public void travel(TreeNode root, int[][] arr){
        if(root == null){
            return;
        }

        arr[0][flag0 ++] = (root.val);

        travel(root.left, arr);
        arr[1][flag1 ++] = (root.val);
        travel(root.right, arr);

        arr[2][flag2 ++] = (root.val);
    }

    public int getTreeSize(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1 + getTreeSize(root.left) + getTreeSize(root.right);
    }

    /**
     * 分别按照二叉树先序，中序和后序打印所有的节点。
     * 输入：
     * {1,2,3}
     * 返回值：
     * [[1,2,3],[2,1,3],[2,3,1]]
     * @author lxq
     * @date 2021/7/2 14:11
     * @param root
     * @return int[][]
     */
    public int[][] threeOrders (TreeNode root) {
        int[][] result = new int[3][];
        List<Integer> first = new ArrayList<>(10);
        List<Integer> mid = new ArrayList<>(10);
        List<Integer> beh = new ArrayList<>(10);

        preorder(root, first);
        midorder(root, mid);
        behorder(root, beh);
        result[0] = first.stream().mapToInt(Integer::valueOf).toArray();
        result[1] = mid.stream().mapToInt(Integer::valueOf).toArray();
        result[2] = beh.stream().mapToInt(Integer::valueOf).toArray();
        return result;
    }

    /**
     * 树的后序遍历
     * @author lxq
     * @date 2021/7/2 14:24
     * @param root
     * @param beh
     */
    private void behorder(TreeNode root, List<Integer> beh) {
        if(root == null){
            return;
        }

        behorder(root.left, beh);
        behorder(root.right, beh);
        beh.add(root.val);
    }

    /**
     * 树的中序遍历
     * @author lxq
     * @date 2021/7/2 14:25
     * @param root
     * @param mid
     */
    private void midorder(TreeNode root, List<Integer> mid) {
        if(root == null){
            return;
        }

        midorder(root.left, mid);
        mid.add(root.val);
        midorder(root.right, mid);
    }

    /**
     * 树的先序遍历
     * @author lxq
     * @date 2021/7/2 14:25
     * @param root
     * @param first
     */
    private void preorder(TreeNode root, List<Integer> first) {
        if(root == null){
            return;
        }

        first.add(root.val);
        preorder(root.left, first);
        preorder(root.right, first);
    }

}
