package com.example.security.redis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public final class RedisPool {
    private static String ADDR="127.0.0.1";
    private static Integer PORT=6379;
    private static String AUTH="123123";

    //可用最大连接的数量，默认为8，-1表示不限制
    private static Integer MAX_TOTAL=10;
    //一个pool最多有多少个状态为idle（空闲）的jedis实例,默认为8
    private static Integer MAX_IDLE=10;
    //等待可用链接的最大时间，单位为毫秒
    //超时抛出JedisConnectionException
    private static Integer MAX_WAIT_MILLTS=10000;
    //
    private static Integer TIMEOUT=10000;
    //在borrow（用）一个jedis实例前，是否验证操作
    //true保证得到的jedis都是可用的
    private static boolean TEST_ON_BORROW=true;
    //
    private static JedisPool jedisPool=null;

    static {
        try{
            JedisPoolConfig config=new JedisPoolConfig();
            config.setMaxTotal(MAX_TOTAL);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT_MILLTS);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool=new JedisPool(config,ADDR,PORT,TIMEOUT,AUTH);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized static Jedis getJedis(){
        try{
            if(jedisPool!=null){
                Jedis jedis=jedisPool.getResource();
                return jedis;
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void returnResource(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
    }
}

