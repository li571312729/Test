package com.designMode.chain.responsibility;

public class Main {
    
    public static void main(String[] args) {
        Request req = new Request();
        req.setMsg("hello! <script> 今天好吗， 欢迎来到996。");
        Response res = new Response();
        res.setMsg("OK! <script> 已收到，欢迎996。");
        FilterChain fc = new FilterChain();
        fc.add(new HtmlFilter())
            .add(new SensityFilter())
            .add(new demoFilter())
             .doFilter(req, res);
        
        System.out.println(req.getMsg() + "\n" + res.getMsg());
    }

}
