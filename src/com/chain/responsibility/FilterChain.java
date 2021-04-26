package com.chain.responsibility;

import java.util.ArrayList;
import java.util.List;

public class FilterChain{

    private List<Filter> list = new ArrayList<>();
    private int index = 0;
    
    public FilterChain add(Filter filter) {
        list.add(filter);
        return this;
    }
    
    public FilterChain remove(Filter filter) {
        list.remove(filter);
        return this;
    }
    
    public List<Filter> getFilters(){
        return list;
    }
    
    public boolean doFilter(Request req, Response res){
        if(index == list.size())        
            return true;
        Filter nextFilter = list.get(index ++);     //获取下一个执行的filter
        return nextFilter.doFilter(req, res, this);
    }
    
    
}
