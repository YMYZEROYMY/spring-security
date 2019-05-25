package com.example.security.repository;

import com.example.security.entity.Actor;
import com.example.security.entity.Movie;
import com.example.security.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findById(int id);

//    @Query("select m from movie m where m.name like '%:s%' or m.director_id in (select d.id from director d where d.name like '%:s%') or m.id in (select movie_id from movies_and_actors,actor a where actor_id=a.id and a.name like '%:s%')")
//    List<Movie> searchMovie(@Param("s") String target);

    List<Movie> findMoviesByNameLikeOrDirector_NameLikeOrActorsContaining(String target1, String target2, Set<Actor> actors);

    List<Movie> findMoviesByTypesContainingOrderByPopularityDesc(Set<Type> types);

    List<Movie> findMoviesByTypesContainsOrderByPopularityDesc(Type type);
}
