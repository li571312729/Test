package com.binaryTree;

public class Test {

    public static void main(String[] args) {
        BinarySearchTree btn = new BinarySearchTree();

//        btn.insert(7);
//        btn.insert(5);
//        btn.insert(9);
//        btn.insert(3);
//        btn.insert(6);
//        btn.insert(8);
//        btn.insert(10);
//        btn.insert(4);
//
//        System.out.println("Inorder traversal of the given tree");
//        btn.inorder();
//
//        System.out.println("\nDelete 3");
//        btn.deleteKey(3);
//        System.out.println("Inorder traversal of the modified tree");
//        btn.inorder();
//        System.out.println("\nSearch for 3: " + btn.search(3));
//
//        System.out.println("\nSearch for 7: " + btn.search(7));
//        System.out.println("\nDelete 7");
//        btn.deleteKey(7);
//        System.out.println("\nSearch for 7: " + btn.search(7));
//
//        System.out.println("\nSearch for 9: " + btn.search(9));
//
//        System.out.println("Inorder traversal of the given tree");
//        btn.inorder();
        btn.insert(7);
        btn.insert(5);
        btn.insert(5);
        btn.deleteKey(5);

    }


}
