package com.movie.dea.controller;

import com.movie.dea.dto.MovieDTO;
import com.movie.dea.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import com.movie.dea.Interfaces.MovieService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

     public MovieController(MovieService movieService) {
         this.movieService = movieService;
     }

     @GetMapping("/pagination")
    public Page<MovieDTO> getPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
     ) {
         return movieService.getMoviesByPage(page     ,  size);
     }

//     @GetMapping("/all")
//     public List<Movie> getMovies(){
//         return movieService.getAllMovie(sort);
//     }


//     @GetMapping("/title/{title}")
//    public List<Movie> getMoviesByTitle(@PathVariable String title) {
//         return movieService.getAllMoviesByTitle(title);
//     }
//

//    @GetMapping("/genre/{genre}")
//    public List<Movie> getAllMoviesByGenre(@PathVariable String genre) {
//        return movieService.getAllMoviesByGenre(genre);
//    }

    @GetMapping("/rating/{minRating}")
    public List<Movie> getMoviesByMinRating(@PathVariable Double minRating) {
        return movieService.getAllMoviesByRating(minRating);
    }

    @GetMapping("/id/{id}")
    public List<Movie> getMoviesById(@PathVariable Integer id) {
        return movieService.getAllMoviesById(id);
    }

    @GetMapping("/date/{ReleaseDate}")
    public List<Movie> getMoviesByDate(@PathVariable LocalDate releaseDate) {
        return movieService.getAllMoviesByDate(releaseDate);
    }


    @PostMapping("/add")
    public Movie creatMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @PutMapping("/update/{id}")
    public Movie updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        movieService.deleteById(id);
    }
}
