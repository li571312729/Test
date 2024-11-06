package com.designMode.责任链结合策略.handleImpl;

import com.designMode.责任链结合策略.Handler;
import com.designMode.责任链结合策略.Strategy;


public class StrategyHandler extends Handler {
    private Strategy strategy;
    public StrategyHandler(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 是否转线下，true转线下
     * @param object
     * @return
     */
    public boolean handleRequest(Object object) {
        boolean execute = strategy.execute(object);
         if (!execute) {
             Handler next = getNext();
             if (next != null) {
                 return next.handleRequest(object);
            }else {
                 return false;
             }
        }
        return execute;
    }
}
