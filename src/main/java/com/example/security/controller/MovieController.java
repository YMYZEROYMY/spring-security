package com.example.security.controller;

import com.example.security.DTO.DTOFullMovie;
import com.example.security.DTO.DTOMovie;
import com.example.security.entity.Actor;
import com.example.security.entity.Director;
import com.example.security.entity.Movie;
import com.example.security.redis.RedisPool;
import com.example.security.service.MovieService;
import org.springframework.beans.factory.support.ManagedSet;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Resource
    private MovieService movieService;

    @RequestMapping("/searchMovie")
    @ResponseBody
    public ArrayList<DTOMovie> searchMovie(String target){
        return movieService.searchMovie(target);
    }

    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id") int id,
                         HttpServletRequest request){
        request.setAttribute("id",id);
        System.out.println("电影id:"+id);
        return "movie_detail";
    }

    @RequestMapping("/getFullMovie")
    @ResponseBody
    public DTOFullMovie getFullMovie(int id){
        System.out.println("获取完全的movie:"+id);
        return movieService.getFullMovie(id);
    }

    @RequestMapping("/getHotMovie")
    @ResponseBody
    public ArrayList<DTOMovie> getHotMovie(){


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
        return movieService.getAll();
    }

    //初始化电影
//    @RequestMapping("/addMovie")
//    @ResponseBody
//    public void addMovie(){
//        movieService.addMovie();
//    }
}
