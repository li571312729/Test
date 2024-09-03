package com.binaryTree;

/**
 * 二叉搜索树
 */
public class BinarySearchTree {

    private Node root;

    /**
     * 查询指定key是否存在二叉搜索树中
     * @param key
     * @return
     */
    public boolean search(int key){
        return searchKey(root, key);
    }

    /**
     * 递归搜索子树
     * @param root
     * @param key
     * @return
     */
    private boolean searchKey(Node root, int key) {
        if (root == null || root.key == key ) return root != null;
        return key < root.key ? searchKey(root.left, key) : searchKey(root.right, key);
    }

    /**
     * 插入元素到二叉搜索树中，如果元素已经存在则直接返回false
     * @param key
     * @return
     */
    public boolean insert(int key){
        if(search(key)){
            System.out.println("插入的元素{" + key + "}已存在，请勿重复插入。");
            return false;
        }
        root = insertKey(root, key);
        return true;
    }

    /**
     * 递归插入子树
     * @param root
     * @param key
     * @return
     */
    private Node insertKey(Node root, int key) {
        if (root == null) return new Node(key);

        if (key < root.key)
            root.left = insertKey(root.left, key);
        else if (key > root.key)
            root.right = insertKey(root.right, key);
        return root;
    }

    /**
     * 删除指定元素
     * @param key
     */
    void deleteKey(int key) {
        root = deleteRec(root, key);
    }

    /**
     * 递归删除元素
     * @param root
     * @param key
     * @return
     */
    private Node deleteRec(Node root, int key) {
        if(root == null) return root;

        if(key < root.key) root.left = deleteRec(root.left, key);
        else if(key > root.key) root.right = deleteRec(root.right, key);
        else{
            if(root.left == null) return root.right;
            else if(root.right == null) return root.left;
            else {
                root.key = rightMin(root.right);
                root.right = deleteRec(root.right, root.key);
            }
        }
        return root;
    }

    /**
     * 当前节点找最小的元素值
     * @param right
     * @return
     */
    private int rightMin(Node right) {
        int min = right.key;
        while (right.left != null){
            min = right.left.key;
            right = right.left;
        }
        return min;
    }

    /**
     * 打印树 中序遍历的形式打印
     */
    void inorder() {
        inorderRec(root);
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }
}


class Node{
    int key;
    Node left, right;

    public Node(int item){
        key = item;
        left = right = null;
    }

    public Node(int key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }
}