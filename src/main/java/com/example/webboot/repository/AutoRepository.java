package com.example.webboot.repository;

import com.example.webboot.dto.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface AutoRepository extends JpaRepository<Auto, Integer> {
}
