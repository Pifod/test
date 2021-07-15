package com.example.webboot.repository;

import com.example.webboot.dto.Trip;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends CrudRepository<Trip, Integer> {

//    @EntityGraph(attributePaths = )
    void multilevelEntities();
}
