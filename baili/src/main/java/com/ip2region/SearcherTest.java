package com.ip2region;

import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaoqiangli
 * @Date 2022-07-14
 * 完全基于文件查询，。文件io
 */
public class SearcherTest {

    public static void main(String[] args) throws Exception {
        // 1、创建 searcher 对象  dbPath = ip2region.xdb file path
        String path = "src/com/ip2region/ip2region.xdb";
        Searcher searcher = null;
        try {
            searcher = Searcher.newWithFileOnly(path);
        } catch (IOException e) {
            System.out.printf("failed to create searcher with `%s`: %s\n", path, e);
            return;
        }

        // 2、查询
        String ip = "36.152.44.196";
        try {
            long sTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));
            System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
        } catch (Exception e) {
            System.out.printf("failed to search(%s): %s\n", ip, e);
        }

        // 3、备注：并发使用，每个线程需要创建一个独立的 searcher 对象单独使用。


        //4.重复查询测试  结果 总37s   平均12us  均3次
        Random random = new Random();
        long avg = 0;
        int avgCount = 0;
        long s = System.nanoTime();
        for (int i = 1; i <= 1000000; i++) {
            ip = random.nextInt(256) + "." + random.nextInt(256) + "." + random.nextInt(256) + "." + random.nextInt(256);
            long sTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));
            avg += cost;
            avgCount += searcher.getIOCount();
            System.out.printf("{i:%6d, ip: %15s, region: %s, ioCount: %d, took: %d μs}\n", i, ip, region, searcher.getIOCount(), cost);
        }
        long time = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - s));
        System.out.printf("总耗时:" + time / 1000 / 1000 + "s, 平均耗时:" + avg / 1000000 + "us," + "平均访问io次数:" + avgCount / 1000000);
    }
}

