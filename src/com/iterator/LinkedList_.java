package com.iterator;

public class LinkedList_<T> implements Collection_<T> {
    
    Node header = null;
    Node tail = null;
    private int size = 0;
    
    @Override
    public void add(T o) {
        Node node = new Node(o);
        node.next = null;
        
        if(header == null){
            header = node;
            tail = node;
        }
        
        tail.next = node;
        tail = node;
        size ++;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator_<T> iterator() {
        return new LinkedListIteator();
    }
    
    private class LinkedListIteator implements Iterator_<T>{

        private Node currentNode = header;
        
        @Override
        public boolean hasNext() {
            if(currentNode == null) 
                return false;
            return true;
        }

        @Override
        public T next() {
            T o = currentNode.o;
            currentNode = currentNode.next;
            return o;
        }
        
    }
    
    private class Node{
        T o;
        Node next;
        
        public Node(T o) {
            super();
            this.o = o;
        }

    }
}
