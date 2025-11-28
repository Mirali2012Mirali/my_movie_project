package com.movie.dea.repository;

import com.movie.dea.entity.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
