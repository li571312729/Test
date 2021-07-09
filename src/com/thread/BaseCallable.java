package com.thread;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.Callable;

/**
 * 多线程带结果任务处理模版
 * @author lxq
 * @date 2021年07月06日 14:30
 */
@Slf4j
public abstract class BaseCallable<V> implements Callable<V> {

    /**
     * 前置处理器，子类通过重写该方法自定义任务处理前的操作
     *
     * @author lxq
     * @date 2021/7/6 14:37
     */
    public void beforPrecess() {
        log.info("任务执行之前...");
    }

    /**
     * 主要处理业务逻辑的方法,需要子类去Override
     *
     * @return
     */
    public abstract V process() throws Exception;

    /**
     * 后置处理，子类通过重写该方法自定义任务处理后的操作
     */
    public void afterProcess() {
        log.info("任务执行之后...");
    }

    @Override
    public V call() throws Exception {
        // 1. 调用任务前置处理方法
        beforPrecess();

        // 2. 执行任务方法
        V process = process();

        // 3. 调用任务后置处理方法
        afterProcess();

        // 4. 返回执行结果
        return process;
    }
}
