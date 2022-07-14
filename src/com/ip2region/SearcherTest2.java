package com.ip2region;

import org.lionsoul.ip2region.xdb.Searcher;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaoqiangli
 * @Date 2022-07-14
 * 整个文件内容缓存，需要文件大小内存 大概10.6 M 数组cBuff 大小
 */
public class SearcherTest2 {

    public static void main(String[] args) throws Exception {
        String dbPath = "src/com/ip2region/ip2region.xdb";

        // 1、从 dbPath 加载整个 xdb 到内存。
        byte[] cBuff;
        try {
            cBuff = Searcher.loadContentFromFile(dbPath);
        } catch (Exception e) {
            System.out.printf("failed to load content from `%s`: %s\n", dbPath, e);
            return;
        }

        // 2、使用上述的 cBuff 创建一个完全基于内存的查询对象。
        Searcher searcher;
        try {
            searcher = Searcher.newWithBuffer(cBuff);
        } catch (Exception e) {
            System.out.printf("failed to create content cached searcher: %s\n", e);
            return;
        }

        // 3、查询
        String ip = "36.152.44.196";
        try {
            long sTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));
            System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
        } catch (Exception e) {
            System.out.printf("failed to search(%s): %s\n", ip, e);
        }

        // 备注：并发使用，用整个 xdb 数据缓存创建的查询对象可以安全的用于并发，也就是你可以把这个 searcher 对象做成全局对象去跨线程访问。
        // 4.重复查询测试    结果 总24s   平均0us  均0次
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
