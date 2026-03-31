package com.movie.dea.Impl;

import com.movie.dea.dto.MovieDTO;
import com.movie.dea.dto.MovieForm;

import com.movie.dea.entity.Director;
import com.movie.dea.entity.Movie;
import com.movie.dea.exception.DirectorNotFoundException;
import com.movie.dea.exception.MovieNotFoundException;
import com.movie.dea.mapper.MovieMapper;
import com.movie.dea.repository.DirectorRepository;
import com.movie.dea.repository.MovieRepository;
import com.movie.dea.Interfaces.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;
    private final MovieMapper movieMapper;

    public MovieServiceImpl(MovieRepository movieRepository, DirectorRepository directorRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getAllMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Movie> getAllMovieByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    @Override
    public List<Movie> getAllMovieByMinRating(Double minRating) {
        return movieRepository.findByMinRating(minRating);
    }

    @Override
    public List<Movie> getAllMovieByReleaseDate(LocalDate releaseDate) {
        return movieRepository.findByReleaseDate(releaseDate);
    }

    @Override
    public Movie createMovie(Movie newMovie) {
        return movieRepository.save(newMovie);
    }

    @Override
    public Movie getMovie(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("No such a movie in db with the following id:  " + id));
    }

    @Override
    public Director getDirector(Integer id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException("No Director with id:" + id));
    }

    @Override
    public void saveForm(MovieForm movieForm) {
        Movie movie;

        if (movieForm.getId() == null) {
            movie = new Movie();
        } else {
            movie = getMovie(movieForm.getId());
        }

        Director director = getDirector(movieForm.getDirectorId());
        movieMapper.updatedEntityForm(movieForm, movie, director);
        movieRepository.save(movie);
    }

    @Override
    public Page<MovieDTO> searchPaginated(int page, int size) {
        return null;
    }

    @Override
    public Page<MovieDTO> searchPaginated(String title, String genre, int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);

        String safeTitle = (title == null) ? "" : title;
        String safeGenre = (genre == null) ? "" : genre;

        Page<Movie> moviePage =
                movieRepository.findByTitleContainingIgnoreCaseAndGenreContainingIgnoreCase
                        (safeTitle, safeGenre, pageable);

        return moviePage.map(movieMapper::toDTO);
    }

    @Override
    public Movie updateMovie(Integer id, Movie updatedMovie) {
        return movieRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedMovie.getTitle());
                    existing.setGenre(updatedMovie.getGenre());
                    existing.setDuration(updatedMovie.getDuration());
                    existing.setRating(updatedMovie.getRating());
                    existing.setReleaseDate(updatedMovie.getReleaseDate());
                    return movieRepository.save(existing);
                })
                .orElseThrow(() -> new MovieNotFoundException("No such a movie with following ID: " + id));
    }

    @Override
    public void deleteById(Integer id) {
        if (!movieRepository.existsById(id)) {
            throw new MovieNotFoundException(
                    "No such a movie with id: " + id
            );
        }
        movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> search(String title, String genre) {
        if (title != null && !title.isBlank()) {
            return movieRepository.findByTitleContainingIgnoreCase(title);
        }

        if (genre != null && !genre.isBlank()) {
            return movieRepository.findByGenre(genre);
        }

        return movieRepository.findAll();
    }

    @Override
    public Page<MovieDTO> getMoviesByPage(int page, int size) {
        return null;
    }

    @Override
    public List<Movie> getAllMoviesByRating(Double minRating) {
        return List.of();
    }

    @Override
    public List<Movie> getAllMoviesById(Integer id) {
        return List.of();
    }

    @Override
    public List<Movie> getAllMoviesByDate(LocalDate releaseDate) {
        return List.of();
    }
}