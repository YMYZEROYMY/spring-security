package com.example.security.service;

import com.example.security.DTO.DTOFullMovie;
import com.example.security.DTO.DTOMovie;
import com.example.security.entity.Actor;
import com.example.security.entity.Director;
import com.example.security.entity.Movie;
import com.example.security.entity.Type;
import com.example.security.repository.ActorRepository;
import com.example.security.repository.DirectorRepository;
import com.example.security.repository.MovieRepository;
import com.example.security.repository.TypeRepository;
import com.example.security.util.DTOChange;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {
    @Resource
    private TypeRepository typeRepository;
    @Resource
    private ActorRepository actorRepository;
    @Resource
    private DirectorRepository directorRepository;
    @Resource
    private MovieRepository movieRepository;

    @Transactional
    public ArrayList<DTOMovie> searchMovie(String target){
        target="%"+target+"%";
        Set<Actor> actors=actorRepository.findActorsByNameLike(target);
        List<Movie> movies=movieRepository.findMoviesByNameLikeOrDirector_NameLikeOrActorsContaining(target,target,actors);
        return DTOChange.movieToDTO(movies);
    }

    @Transactional
    public ArrayList<DTOMovie> getAll(){
        List<Movie> movies= movieRepository.findAll();
        return DTOChange.movieToDTO(movies);
    }

    @Transactional
    public DTOFullMovie getFullMovie(int id){
        Movie movie=movieRepository.findById(id);
        return new DTOFullMovie(movie);
    }

    @Transactional
    public void addMovie() {
        Type type1 = new Type("动作");
        Type type2 = new Type("剧情");
        Type type3 = new Type("热血");
        Type type4 = new Type("爱情");

        List<Type> types = new ArrayList<>();
        types.add(type1);
        types.add(type2);
        types.add(type3);
        types.add(type4);
        types = typeRepository.saveAll(types);

        Director director1 = new Director("安东尼·罗素");
        Director director2 = new Director("郭帆");
        Director director3 = new Director("尾田荣一郎");
        Director director4 = new Director("宫繁之");
        Director director5 = new Director("荒木哲郎");

        List<Director> directors = new ArrayList<>();
        directors.add(director1);
        directors.add(director2);
        directors.add(director3);
        directors.add(director4);
        directors.add(director5);

        directors = directorRepository.saveAll(directors);

        for(Director temp:directors){
            switch (temp.getName()){
                case "安东尼·罗素":
                    director1=temp;
                    break;
                case "郭帆":
                    director2=temp;
                    break;
                case "尾田荣一郎":
                    director3=temp;
                    break;
                case "宫繁之":
                    director4=temp;
                    break;
                case "荒木哲郎":
                    director5=temp;
                    break;
            }
        }

        Actor actor1 = new Actor("小罗伯特·唐尼");
        Actor actor2 = new Actor("克里斯·埃文斯");
        Actor actor3 = new Actor("斯嘉丽·约翰逊");
        Actor actor4 = new Actor("保罗·路德");
        Actor actor5 = new Actor("马克·陆法洛");

        List<Actor> actors = new ArrayList<>();
        actors.add(actor1);
        actors.add(actor2);
        actors.add(actor3);
        actors.add(actor4);
        actors.add(actor5);

        actors=actorRepository.saveAll(actors);

//        Movie movie1=new Movie("复仇者联盟",director1,actors,types,new Date(),100,100,75.3);
//        Movie movie2=new Movie("流浪地球",director2,actors,types,new Date(),90,150,34);
//        Movie movie3=new Movie("海贼王",director3,actors,types,new Date(),70,70,20);
//        Movie movie4=new Movie("消灭都市",director4,actors,types,new Date(),10,5,20);
//        Movie movie5=new Movie("进击的巨人",director5,actors,types,new Date(),50,30,25.5);

        List<Movie> movies=new ArrayList<>();
//        movies.add(movie1);
//        movies.add(movie2);
//        movies.add(movie3);
//        movies.add(movie4);
//        movies.add(movie5);
        movieRepository.saveAll(movies);

    }

}
