package com.bigmap;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * 向文件中写入40亿数字
 *
 * @author xiaoqiangli
 * @Date 2021-11-30
 */
@Slf4j
public class GenerateFileNumber {

    static ThreadFactory basicThreadFactory;

    static ThreadPoolExecutor threadPoolExecutor = null;

    static {
        basicThreadFactory = new ThreadFactoryBuilder().setNameFormat("GenerateNumber-").build();
        threadPoolExecutor = new ThreadPoolExecutor(
                12, 101,
                15, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000000),
               // basicThreadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static void main(String[] args) {
        int count = 100;
        for (int i = 1; i <= count; i++) {
            threadPoolExecutor.execute(new NumberThreadTest((long) i));
        }
        threadPoolExecutor.shutdown();
    }

}

@Slf4j
class NumberThreadTest extends Thread{

    private Long up;
    private Long down;
    private static LongAdder count = new LongAdder();
    private static FileWriter out;

    static {
        File f = new File("D:\\Users\\xiaoqiangli\\work\\qq.txt");
        try {
            if (!f.exists() && !f.createNewFile()) {
                log.warn("file created fail。");
                System.exit(-1);
            }
            out = new FileWriter(f);
        } catch (IOException e) {
            log.error("file created fail：{}", e);
            System.exit(-1);
        }
    }

    public NumberThreadTest(Long number) {
        this.up = number * 4000000;
        this.down = (number - 1) * 4000000 + 1;
    }

    @Override
    public void run() {
        while (down <= up) {
            try {
                out.append(down + "\n");
                log.info(Thread.currentThread().getName() + "   生成元素：{}", down++);
                count.increment();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info(Thread.currentThread().getName() + "目前为止共计生成元素{}个", count.sum());
    }

}