package com.designMode.责任链结合策略;

import com.designMode.责任链结合策略.strategyImpl.GeneralStrategy;
import com.designMode.责任链结合策略.strategyImpl.LeaveStrategy;
import com.designMode.责任链结合策略.strategyImpl.ReimbursementStrategy;

public enum ConditionTypeEnum {
    LEAVE("leave", new LeaveStrategy()),
    REIMBURSEMENT("reimbursement", new ReimbursementStrategy()),
    GENERAL("general", new GeneralStrategy()),

    ;


    public static Strategy getStrategy(String type) {
        for (ConditionTypeEnum conditionTypeEnum : ConditionTypeEnum.values()) {
            if (conditionTypeEnum.type.equals(type)) {
                return conditionTypeEnum.strategy;
            }
        }
        return null;
    }

    private String type;
    private Strategy strategy;

    ConditionTypeEnum(String type, Strategy strategy) {
        this.type = type;
        this.strategy = strategy;
    }
}
