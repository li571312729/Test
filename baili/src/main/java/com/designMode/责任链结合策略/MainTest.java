package com.designMode.责任链结合策略;


import com.designMode.责任链结合策略.dto.Condition;

import java.util.Arrays;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        // 已配置的条件
        List<Condition> conditions = Arrays.asList(
                Condition.builder().type("leave").open(true).desc("请假条件").build(),
                Condition.builder().type("reimbursement").open(true).desc("报销条件").build(),
                Condition.builder().type("general").open(true).desc("常规检查条件").build(),
                Condition.builder().type("special").open(false).desc("特殊检查条件").build());
        // 构造处理链
        Handler handler = HandlerBuilder.buildBuilder(conditions);
        // 处理请求
        if(handler.handleRequest(null)){
            System.out.println("转线下");
        }else {
            System.out.println("转线上");
        };
    }

}