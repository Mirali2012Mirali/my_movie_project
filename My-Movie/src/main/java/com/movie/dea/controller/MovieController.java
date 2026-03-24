package com.movie.dea.controller;


import com.movie.dea.Interfaces.MovieService;
import com.movie.dea.dto.MovieDTO;
import com.movie.dea.entity.Movie;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/movies")
@Tag(name = "Movie management APIs ")
public class MovieController {
    private final com.movie.dea.controller.MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping
    @Operation(summary = "Get all movies")
    public List<Movie> getMovie() {
    return movieService.getAllMovie();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get movie by ID")
    public Movie getMovie(@PathVariable Integer id) {
        return movieService.getMovie(id);
    }

    @PostMapping
    @Operation(summary = "Create Movie")
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

}

