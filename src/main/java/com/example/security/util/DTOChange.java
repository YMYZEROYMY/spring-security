package com.example.security.util;

import com.example.security.DTO.DTOMovie;
import com.example.security.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class DTOChange {
    public synchronized static ArrayList<DTOMovie> movieToDTO(List<Movie> movies){
        ArrayList<DTOMovie> dtoMovies=new ArrayList<>();
        for(Movie movie:movies){
            DTOMovie dtoMovie=new DTOMovie(movie);
            dtoMovies.add(dtoMovie);
        }
        return dtoMovies;
    }
}
