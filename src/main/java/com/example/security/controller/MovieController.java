package com.example.security.controller;

import com.example.security.entity.Movie;
import com.example.security.redis.RedisPool;
import org.springframework.beans.factory.support.ManagedSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/movie")
public class MovieController {
    @RequestMapping("/getHotMovie")
    @ResponseBody
    public ArrayList<Movie> getHotMovie(){
//        Jedis jedis=RedisPool.getJedis();
//        ArrayList<Movie> target=new ArrayList<>();
//        assert jedis != null;
//        Set<String> movies=jedis.smembers("movies");
//        for(String movieName:movies){
//            Movie movie=new Movie();
//            movie.setName(movieName);
//            movie.setPath("img/"+movieName+".jpg");
//            movie.setInfo(jedis.hget("movieInfo",movieName));
//            target.add(movie);
//        }

//        Movie movie1=new Movie("电影1","我的电影1","img/01.png");
//        Movie movie2=new Movie("电影2","我的电影2","img/02.png");
//        Movie movie3=new Movie("电影3","我的电影3","img/03.png");
//        Movie movie4=new Movie("电影4","我的电影4","img/04.png");
//        movies.add(movie1);
//        movies.add(movie2);
//        movies.add(movie3);
//        movies.add(movie4);

//        RedisPool.returnResource(jedis);
//        return target;
        return null;
    }
}
