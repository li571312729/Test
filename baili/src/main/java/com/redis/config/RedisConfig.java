package com.redis.config;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Administrator
 */
@Slf4j
public class RedisConfig {

    public static Jedis getRedis(){
        JedisPoolConfig poolCfg = new JedisPoolConfig();
        //最大空闲数
        poolCfg.setMaxIdle(50);
        //最大连接数
        poolCfg.setMaxTotal(100);
        //最大等待毫秒数
        poolCfg.setMaxWaitMillis(20000);
        //使用配置创建连接池
        JedisPool pool = new JedisPool (poolCfg, "localhost");
        //从连接池中获取单个连接
        Jedis jedis = pool.getResource();
        //如果需密码
        //jedis.auth("password");
        return jedis;
    }



}
