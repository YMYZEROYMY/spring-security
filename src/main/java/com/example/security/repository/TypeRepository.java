package com.example.security.repository;

import com.example.security.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TypeRepository extends JpaRepository<Type,Integer> {
    Set<Type> findByName(String target);
}
