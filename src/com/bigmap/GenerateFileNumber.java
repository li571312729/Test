package com.bigmap;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 向文件中写入40亿数字
 *
 * @author xiaoqiangli
 * @Date 2021-11-30
 */
@Slf4j
public class GenerateFileNumber {

    static ThreadPoolExecutor threadPoolExecutor = null;

    static {
        threadPoolExecutor = new ThreadPoolExecutor(
                12, 101,
                15, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000000),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static void main(String[] args) {

        AtomicLong count = new AtomicLong();

        File f = new File("D:\\Users\\xiaoqiangli\\work\\qq.txt");
        try {
            if (!f.exists() && !f.createNewFile()) {
                log.warn("file created fail。");
                return;
            }
        } catch (IOException e) {
            log.error("file created fail：{}", e);
            System.exit(-1);
        }

        try (
                FileWriter out = new FileWriter(f);
        ) {
            while (count.incrementAndGet() < Long.parseLong("4112314521")) {
                threadPoolExecutor.execute(() -> {
                    try {
                        out.append(count.get() + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                log.info("已生成元素{}个", count.get());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("元素生成结束，总计{}个", count.get());
    }

}
