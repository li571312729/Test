package com.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/**
 * @author lxq
 * @date 2021年07月06日 14:41
 */
@Slf4j
public class TaskB extends BaseCallable<Integer>{

    // 待处理的数据
    private String data;

    public TaskB(String data) {
        this.data = data;
    }

    @Override
    public Integer process() {
        log.info("TaskB执行完毕");
        return data.length();
    }
}
