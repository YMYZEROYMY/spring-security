package com.example.security.repository;

import com.example.security.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director,Integer> {
}
