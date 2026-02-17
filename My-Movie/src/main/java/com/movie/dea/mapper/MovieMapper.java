package com.movie.dea.mapper;

import com.movie.dea.dto.MovieDTO;
import com.movie.dea.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieDTO toDTO(Movie movie) {
        MovieDTO dto = new MovieDTO();

        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setGenre(movie.getGenre());
        dto.setReleaseDate(movie.getReleaseDate());
        dto.setRating(movie.getRating());
        dto.setDuration(movie.getDuration());

        if (movie.getDirector() != null) {
            dto.setDirectorId(movie.getId());
            dto.setDirectorName(movie.getDirector().getName);
        }
    }
}
