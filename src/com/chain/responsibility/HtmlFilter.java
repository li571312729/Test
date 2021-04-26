package com.chain.responsibility;

public class HtmlFilter implements Filter {

    @Override
    public boolean doFilter(Request req, Response res, FilterChain chain) {
        String content = req.getMsg();
        if(content.contains("<") && content.contains(">")){
            req.setMsg(content.replace("<", "[").replace(">", "]"));
        }
        
        if(!chain.doFilter(req, res)){
            return false;
        }
        
        content = res.getMsg();
        if(content.contains("<") && content.contains(">")){
            res.setMsg(content.replace("<", "[").replace(">", "]"));
        }
        return true;
    }

}
