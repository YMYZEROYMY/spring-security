package com.example.security.repository;

import com.example.security.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ActorRepository extends JpaRepository<Actor,Integer> {
    Set<Actor> findActorsByNameLike(String target);
}
