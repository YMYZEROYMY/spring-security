package com.example.security.DTO;

import com.example.security.entity.Actor;
import com.example.security.entity.Movie;
import com.example.security.entity.Type;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class DTOFullMovie extends DTOMovie {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private ArrayList<String> actorsName;
    private ArrayList<String> typesName;

    public DTOFullMovie(Movie movie) {
        super(movie);
        actorsName = new ArrayList<>();
        typesName = new ArrayList<>();

        date = movie.getDate();
        Set<Actor> actors = movie.getActors();
        Set<Type> types = movie.getTypes();
        for (Actor actor : actors) {
            actorsName.add(actor.getName());
        }
        for (Type type : types) {
            typesName.add(type.getName());
        }
    }

    public ArrayList<String> getActorsName() {
        return actorsName;
    }

    public void setActorsName(ArrayList<String> actorsName) {
        this.actorsName = actorsName;
    }

    public ArrayList<String> getTypesName() {
        return typesName;
    }

    public void setTypesName(ArrayList<String> typesName) {
        this.typesName = typesName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
