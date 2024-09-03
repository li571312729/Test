package com.thread.threadPool;

import com.thread.BaseCallable;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author lxq
 * @date 2021年07月06日 14:54
 */
public interface IConcurrentThreadPool {
    /**
     * 初始化线程池
     */
    void initConcurrentThreadPool();

    /**
     * 提交单个任务
     *
     * @param task
     * @param <V>
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    <V> V submit(BaseCallable<V> task) throws InterruptedException,
            ExecutionException;

    /**
     * 提交多个任务
     *
     * @param tasks
     * @param <V>
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    <V> List<V> invokeAll(List<? extends BaseCallable<V>> tasks)
            throws InterruptedException, ExecutionException;
}