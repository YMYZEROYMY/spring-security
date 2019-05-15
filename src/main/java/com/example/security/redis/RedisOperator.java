package com.example.security.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class RedisOperator {
    private Jedis jedis;

    @Before
    public void setJedis() {
        jedis = RedisPool.getJedis();
        System.out.println("连接Redis成功");
    }

    @After
    public void closeJedis(){
        RedisPool.returnResource(jedis);
        System.out.println("关闭Jedis成功");
    }

    @Test
    public void addMovies(){
        String movie1="复仇者联盟3";
        String movie2="海贼王";
        String movie3="进击的巨人";
        String movie4="流浪地球";
        String movie5="消灭都市";
        String info1="最后之战的前夕";
        String info2="小伙伴大冒险";
        String info3="那一天，人们回想起被巨人支配的恐惧";
        String info4="国产科技巨作";
        String info5="日本动漫";
        jedis.sadd("movies",movie1,movie2,movie3,movie4,movie5);

        Map<String,String> movieInfo=new HashMap<>();
        movieInfo.put(movie1,info1);
        movieInfo.put(movie2,info2);
        movieInfo.put(movie3,info3);
        movieInfo.put(movie4,info4);
        movieInfo.put(movie5,info5);

        jedis.hmset("movieInfo",movieInfo);
    }
}
