package com.designMode.责任链结合策略;

import com.designMode.责任链结合策略.dto.Condition;
import com.designMode.责任链结合策略.factory.HandlerFactory;

import java.util.List;

public class HandlerBuilder {
    public static Handler buildBuilder(List<Condition> handlerConfigs) {
        Handler firstHandler = null;
        Handler currentHandler = null;

        for (Condition condition : handlerConfigs) {
            // 条件配置为空或不启用，则直接跳过
            if (condition == null || !condition.isOpen()) {
                continue;
            }
            Handler handler = HandlerFactory.createHandler(condition.getType());
            if (firstHandler == null) {
                firstHandler = handler;
                currentHandler = handler;
            } else {
                currentHandler.setNext(handler);
                currentHandler = handler;
            }
        }

        return firstHandler;
    }
}
