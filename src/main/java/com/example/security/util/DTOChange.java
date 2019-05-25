package com.example.security.util;

import com.example.security.DTO.DTOInvoice;
import com.example.security.DTO.DTOMovie;
import com.example.security.entity.Invoice;
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
        dtoMovies.sort(new MovieComparator());
        return dtoMovies;
    }

    public synchronized static ArrayList<DTOInvoice> invoicesToDTO(List<Invoice> invoices){
        ArrayList<DTOInvoice> dtoInvoices=new ArrayList<>();
        for (Invoice invoice:invoices){
            DTOInvoice dtoInvoice=new DTOInvoice(invoice);
            dtoInvoices.add(dtoInvoice);
        }
        return dtoInvoices;
    }
}
