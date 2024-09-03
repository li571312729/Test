package com.thread.threadPool;

import com.thread.BaseCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author lxq
 * @date 2021年07月06日 14:55
 */
public class ConcurrentThreadPool implements IConcurrentThreadPool {

    private ThreadPoolExecutor threadPoolExecutor;
    /**
     * 核心线程数
     */
    private int corePoolSize = 50;
    /**
     * 最大线程数
     */
    private int maximumPoolSize = 200;
    /**
     * 超时时间30秒
     */
    private long keepAliveTime = 30;

    @Override
    public void initConcurrentThreadPool() {
        // 采用默认的线程工厂和默认的拒绝策略
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());
    }

    @Override
    public <V> V submit(BaseCallable<V> task) throws InterruptedException,
            ExecutionException {
        Future<V> result = threadPoolExecutor.submit(task);
        threadPoolExecutor.shutdown();
        return result.get();
    }

    @Override
    public <V> List<V> invokeAll(List<? extends BaseCallable<V>> tasks)
            throws InterruptedException, ExecutionException {

        List<Future<V>> tasksResult = threadPoolExecutor.invokeAll(tasks);
        List<V> resultList = new ArrayList<V>();

        for (Future<V> future : tasksResult) {
            resultList.add(future.get());
        }
        threadPoolExecutor.shutdown();
        return resultList;
    }

}
