package com.designMode.chain.responsibility;

public class demoFilter implements Filter {
    
    @Override
    public boolean doFilter(Request req, Response res, FilterChain chain) {
        req.setMsg(req.getMsg() + "   --- baili");
        
        if(!chain.doFilter(req, res)){
            return false;
        }
        
        res.setMsg(res.getMsg() + "   --- baili");
        return true;
    }

    
}