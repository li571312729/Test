package com.designMode.chain.responsibility;

public class SensityFilter implements Filter {

    @Override
    public boolean doFilter(Request req, Response res, FilterChain chain) {
        String content = req.getMsg();
        if(content.contains("996")){
            req.setMsg(content.replace("996", "955"));
        }
        
        if(!chain.doFilter(req, res)){
            return false;
        }
        
        content = res.getMsg();
        if(content.contains("996")){
            res.setMsg(content.replace("996", "955"));
        }
        return true;
    }

}
