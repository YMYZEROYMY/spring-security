package com.example.security.DTO;

import com.example.security.entity.Movie;
import org.springframework.beans.BeanUtils;

public class DTOMovie {
    private int id;
    private String name;
    private String path;
    private Double price;
    private String directorName;
    private int ticket;
    private int popularity;

    public DTOMovie(Movie movie){
        BeanUtils.copyProperties(movie,this);
        directorName = movie.getDirector().getName();
        path="/img/"+getName()+".jpg";
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
