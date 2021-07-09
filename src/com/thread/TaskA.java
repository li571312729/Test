package com.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author lxq
 * @date 2021年07月06日 14:41
 */
@Slf4j
public class TaskA extends BaseCallable<String>{

    // 待处理的数据
    private List<Integer> data;

    public TaskA(List<Integer> data) {
        this.data = data;
    }

    @Override
    public String process() throws Exception {
        Optional.ofNullable(data).orElseThrow(() -> new Exception("TaskA任务数据缺失"));
        data.stream().forEach(System.out::println);
        log.info("TaskA执行完毕");
        return "TaskA执行完毕,共打印" + data.size() + "条数据";
    }
}
