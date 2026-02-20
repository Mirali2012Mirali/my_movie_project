package com.movie.dea.controller;


import com.movie.dea.entity.Movie;
import com.movie.dea.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

//    @GetMapping("/pagination")
//    public Page<Movie> getPagination(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "5") int size
//    ) {
//        return movieService.getMoviesByPage(page, size);
//    }


    @GetMapping("/all")
    public List<Movie> getMovies(){
        return movieService.getAllMovie();
    }

    @GetMapping("/title/{title}")
    public List<Movie> getMoviesByTitle(@PathVariable String title) {
        return movieService.getAllMovieByTitle(title);
    }

    @GetMapping("/genre/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre) {
        return movieService.getAllMovieByTitle(genre);
    }

    @GetMapping("/rating/{minRating}")
    public List<Movie> getMoviesByMinRating(@PathVariable Double minRating) {
        return movieService.getAllMovieByMinRating(minRating);
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Integer id) {
        return movieService.getMovie(id);
    }

    @GetMapping("/date/{releaseDate}")
    public List<Movie> getMovieByDate(@PathVariable LocalDate releaseDate) {
        return movieService.getAllMovieByReleaseDate(releaseDate);
    }

    @PostMapping("/add")
    public Movie createMovie(@PathVariable Movie movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping("/update/{id}")
    public Movie updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMovie(@PathVariable Integer id) {
        movieService.deleteById(id);
    }
}
