package com.redis;

import com.redis.config.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * @author Administrator
 */
@Slf4j
public class RedisTest {
    public static void main(String[] args) {
        pipelineTest();
        secondTest();
    }

    /**
     * 普通情况下使用连接池，无管道pipeline测试1S操作次数
     */
    public static void secondTest(){
        Jedis jedis = RedisConfig.getRedis();
        // jedis.auth("password"); //如果需密码
        int i = 0;
        try {
            long start = System.currentTimeMillis(); // 开始毫秒数
            while (true) {
                long end = System.currentTimeMillis();
                if (end - start >= 1000) {
                    // 当大于等于1000毫秒（相当于1秒）时，结束操作
                    break;
                }
                i++;
                jedis.set("test" + i, i + "");
                jedis.get("test" + i);
            }
        } finally {
            // 关闭连接
            jedis.close();
        }
        // 打印1秒内对Redis的操作次数
        System.out.println("reids每秒操作：" + i + "次");
    }

    /**
     * 使用连接池，有管道pipeline测试操作次数
     */
    public static void pipelineTest(){
        Jedis jedis = RedisConfig.getRedis();

        long start = System.currentTimeMillis();
        // 开启流水线
        Pipeline pipeline = jedis.pipelined();
        // 这里测试10万条的读/写2个操作
        for (int i = 0; i < 100000; i++) {
            int j = i + 1;
            pipeline.set("pipeline_key_" + j, "pipeline_value_" + j);
            pipeline.get("pipeline_key_" + j);
        }

        // pipeline.sync(); //这里只执行同步，但是不返回结果
        // pipeline.syncAndReturnAll ();将返回执行过的命令返回的List列表结果
        List result = pipeline.syncAndReturnAll();

        long end = System.currentTimeMillis();

        result.forEach(o -> {
            log.info(o.toString());
        });

        // 计算耗时
        System.err.println("耗时：" + (end - start) + "毫秒");
    }

}