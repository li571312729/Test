package com.designMode.composite;

import java.util.ArrayList;
import java.util.List;

public class BranchNode extends Node {
    
    private List<Node> childNodes = new ArrayList<>();
    private String Name;
    
    public BranchNode(String name) {
        super();
        Name = name;
    }
    
    public BranchNode(List<Node> childNodes, String name) {
        super();
        this.childNodes = childNodes;
        Name = name;
    }
    
    @Override
    void print() {
        System.out.println(this.Name);
    }
    
    public BranchNode addChildNode(Node e) {
        childNodes.add(e);
        return this;
    }

    public BranchNode removeChildNode(Node e) {
        childNodes.remove(e);
        return this;
    }

    public List<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<Node> childNodes) {
        this.childNodes = childNodes;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
