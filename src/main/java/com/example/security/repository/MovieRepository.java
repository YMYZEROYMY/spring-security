package com.example.security.repository;

import com.example.security.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository <Movie,Integer>{

}
