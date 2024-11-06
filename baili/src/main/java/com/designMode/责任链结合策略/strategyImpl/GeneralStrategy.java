package com.designMode.责任链结合策略.strategyImpl;

import com.designMode.责任链结合策略.Strategy;


public class GeneralStrategy implements Strategy {
    @Override
    public boolean execute(Object object) {
        int a = (int)(Math.random() * 100);
        System.out.println("处理通用请求: " + a);
        if(a > 70){
            return true;
        }
        return false;
    }
}
