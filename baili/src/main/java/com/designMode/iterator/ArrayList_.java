package com.designMode.iterator;

@SuppressWarnings("unchecked")
public class ArrayList_<T> implements Collection_<T> {
    
    private T[] object;
    //已存放元素个数
    private int size = 0;
    
    public ArrayList_() {
        super();
        object = (T[]) new Object[10];
    }

    public ArrayList_(int size) {
        super();
        object = (T[]) new Object[size];
    }

    @Override
    public void add(T o) {
        if(size == object.length) { 
            int newSize = object.length << 1;
            T[] tempObject = (T[]) new Object[newSize];
            System.arraycopy(object, 0, tempObject, 0, object.length);
            object = tempObject;
        }
        
        object[size] = o;
        size ++;
    }
    
    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator_<T> iterator() {
        return new ArrayListIteator();
    }

    private class ArrayListIteator implements Iterator_<T> {

        private int index = 0;
        
        @Override
        public boolean hasNext() {
            if(index >= size) return false;
            return true;
        }

        @Override
        public T next() {
            return object[index ++];
        }
        
    }
}
