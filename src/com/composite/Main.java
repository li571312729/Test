package com.composite;

public class Main {
    
    public static void main(String[] args) {
        BranchNode root = new BranchNode("BOSS");
        BranchNode mangaer1 = new BranchNode("Manager1");
        BranchNode mangaer2 = new BranchNode("Manager2");
        BranchNode mangaer3 = new BranchNode("Manager3");
        
        BranchNode group1 = new BranchNode("Group1");
        BranchNode group2 = new BranchNode("Group2");
        BranchNode group3 = new BranchNode("Group3");
        BranchNode group4 = new BranchNode("Group4");
        BranchNode group5 = new BranchNode("Group5");
        BranchNode group6 = new BranchNode("Group6");
        
        LeafNode staff1 = new LeafNode("Staff1");
        LeafNode staff2 = new LeafNode("Staff2");
        LeafNode staff3 = new LeafNode("Staff3");
        LeafNode staff4 = new LeafNode("Staff4");
        
        root.addChildNode(mangaer1).addChildNode(mangaer2).addChildNode(mangaer3);
        mangaer1.addChildNode(group1).addChildNode(group2);
        mangaer2.addChildNode(group3).addChildNode(group4);
        mangaer3.addChildNode(group5).addChildNode(group6);
        group1.addChildNode(staff1);
        group3.addChildNode(staff2);
        group4.addChildNode(staff3);
        group6.addChildNode(staff4);
        
        printTree(root, 0);
    }

    private static void printTree(Node node, int depath) {
        for (int j = 0; j < depath; j++) System.out.print("  ");
        node.print();
        
        if(node instanceof BranchNode){
            for (Node e : ((BranchNode) node).getChildNodes()) {
                printTree(e, depath + 1);
            }
        }
    }
}
