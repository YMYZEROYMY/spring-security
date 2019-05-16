package com.example.security.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Type implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(targetEntity = Movie.class, cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "types")
    private List<Movie> movies;

    public Type() {
        super();
    }

    public Type(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
