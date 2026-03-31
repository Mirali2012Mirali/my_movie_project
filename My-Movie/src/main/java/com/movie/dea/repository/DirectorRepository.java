package com.movie.dea.repository;


import com.movie.dea.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
    Optional<Director> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}