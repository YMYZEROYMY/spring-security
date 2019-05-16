package com.example.security.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne(targetEntity = Director.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    @ManyToMany(targetEntity = Actor.class, cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "movies_and_actors",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id", nullable = false))
    private List<Actor> actors;

    @ManyToMany(targetEntity = Type.class, cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "movies_and_types",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false))
    private List<Type> types;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private int popularity;
    private int ticket;
    private double price;

    public Movie() {
        super();
    }

    public Movie(String name, Director director, List<Actor> actors, List<Type> types, Date date, int popularity, int ticket, double price) {
        this.name = name;
        this.director = director;
        this.actors = actors;
        this.types = types;
        this.date = date;
        this.popularity = popularity;
        this.ticket = ticket;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}

