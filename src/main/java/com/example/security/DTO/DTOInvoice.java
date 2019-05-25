package com.example.security.DTO;

import com.example.security.entity.Invoice;
import com.example.security.entity.Movie;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class DTOInvoice {
    private int movieId;
    private String movieName;
    private Date date;
    private int number;
    private double sum;

    public DTOInvoice(){
        super();
    }

    public DTOInvoice(Invoice invoice){
        BeanUtils.copyProperties(invoice,this);
        Movie movie=invoice.getMovie();
        this.movieId=movie.getId();
        this.movieName=movie.getName();
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
