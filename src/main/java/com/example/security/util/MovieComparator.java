package com.example.security.util;

import com.example.security.DTO.DTOMovie;

import java.util.Comparator;

public class MovieComparator implements Comparator<DTOMovie> {
    @Override
    public int compare(DTOMovie o1, DTOMovie o2) {
        return o2.getPopularity()-o1.getPopularity();
    }
}
