package com.solution;

import java.util.Arrays;

/**
 * @author lxq
 * @date 2021年10月15日 15:03
 */
public class 从前序与中序遍历序列构造二叉树 {

    public static void main(String[] args) {
        TreeNode treeNode = buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(111);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || inorder.length == 0){
            return null;
        }
        TreeNode root =new TreeNode (preorder[0]);
        for(int i = 0; i < preorder.length; i++){
            if(preorder[0] == inorder[i]){
                root.left = buildTree(Arrays.copyOfRange(preorder,1,i + 1),
                        Arrays.copyOfRange(inorder,0,i));
                root.right = buildTree(Arrays.copyOfRange(preorder,i + 1, preorder.length),
                        Arrays.copyOfRange(inorder,i + 1, inorder.length));
                break;
            }
        }
        return root;
    }
}
