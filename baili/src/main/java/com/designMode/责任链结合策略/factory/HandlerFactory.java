package com.designMode.责任链结合策略.factory;

import com.designMode.责任链结合策略.ConditionTypeEnum;
import com.designMode.责任链结合策略.Handler;
import com.designMode.责任链结合策略.Strategy;
import com.designMode.责任链结合策略.handleImpl.StrategyHandler;

import java.util.Objects;

public class HandlerFactory {
    public static Handler createHandler(String type) {
        Strategy strategy = ConditionTypeEnum.getStrategy(type);
        if(Objects.isNull(strategy)){
            throw new IllegalArgumentException("未知的请求类型: " + type);
        }
        return new StrategyHandler(strategy);
    }
}
