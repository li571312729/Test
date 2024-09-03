package com.ip2region;

import org.lionsoul.ip2region.xdb.Searcher;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaoqiangli
 * @Date 2022-07-14
 * 基于目录的缓存，把目录缓存(大概 0.875M  就是 vIndex数组大小)，这样少一次目录的io
 */
public class SearcherTest1 {

    public static void main(String[] args) throws Exception {
        String dbPath = "src/com/ip2region/ip2region.xdb";

        // 1、从 dbPath 中预先加载 VectorIndex 缓存，并且把这个得到的数据作为全局变量，后续反复使用。
        byte[] vIndex;
        try {
            vIndex = Searcher.loadVectorIndexFromFile(dbPath);
        } catch (Exception e) {
            System.out.printf("failed to load vector index from `%s`: %s\n", dbPath, e);
            return;
        }

        // 2、使用全局的 vIndex 创建带 VectorIndex 缓存的查询对象。
        Searcher searcher;
        try {
            searcher = Searcher.newWithVectorIndex(dbPath, vIndex);
        } catch (Exception e) {
            System.out.printf("failed to create vectorIndex cached searcher with `%s`: %s\n", dbPath, e);
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

        // 备注：每个线程需要单独创建一个独立的 Searcher 对象，但是都共享全局的制度 vIndex 缓存。

        // 4.重复查询测试    结果 总35s   平均9us  均2次
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
