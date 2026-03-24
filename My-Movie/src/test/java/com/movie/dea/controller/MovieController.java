package com.movie.dea.controller;

import com.movie.dea.Interfaces.MovieService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@Tag(name="")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
}