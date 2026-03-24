package com.movie.dea.Interfaces;


import com.movie.dea.dto.MovieDTO;
import com.movie.dea.dto.MovieForm;
import com.movie.dea.entity.Director;
import com.movie.dea.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface MovieService {

    List<Movie> getAllMovie();

    List<Movie> getAllMovieByTitle(String title);

    List<Movie> getAllMovieByGenre(String genre);

    List<Movie> getAllMovieByMinRating(Double minRating);

    List<Movie> getAllMovieByReleaseDate(LocalDate releaseDate);

    Movie createMovie(Movie newMovie);

    Movie getMovie(Integer id);

    Director getDirector(Integer id);

    void saveForm(MovieForm movieForm);

    Page<MovieDTO> searchPaginated(int page, int size);

    Movie updateMovie(Integer id, Movie updatedMovie);

    void deleteById(Integer id);

        List<Movie> search(String title,String genre);
}
