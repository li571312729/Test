package com.angorithm;

public class 翻转二叉树 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(4);

        invertTree(root);
        System.out.println(111111111);
    }


    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }


//    public static TreeNode invertTree(TreeNode root) {
//        if(root == null){
//            return null;
//        }
//        TreeNode t = null;
//
//        if(root.left != null && (root.left.left != null || root.left.right != null)){
//            invertTree(root.left);
//        }else {
//            if(root.left != null){
//                t = root.left.left;
//                root.left.left = root.left.right;
//                root.left.right = t;
//            }
//        }
//
//        if(root.right != null && (root.right.left != null || root.right.right != null)){
//            invertTree(root.right);
//        }else {
//            if(root.right != null){
//                t = root.right.left;
//                root.right.left = root.right.right;
//                root.right.right = t;
//            }
//        }
//
//        t = root.left;
//        root.left = root.right;
//        root.right = t;
//        return root;
//    }
//}

}
