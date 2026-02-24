package com.movie.dea.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieForm {

    private Integer id;

    @NotBlank(message = "Title is required!")
    @Size(min = 5, max = 100, message = "Title must be 5-100 chars.")
    private String title;
    @NotBlank(message = "Genre is required!")
    @Size(min = 5, max = 100, message = "Genre must be 5-100 chars.")
    private String genre;


    @NotNull(message = "don't leave empty")
    private LocalDate releaseDate;

    @NotNull(message = "don't leave empty")
    @DecimalMin(value = "1.0", message = "Rating must be at least 0")
    @DecimalMax(value = "10.0", message = "Rating must be at most 10")

    private Double rating;

    @NotBlank(message = "Duration is required!")
    @Size(min = 2, max = 3, message = "Genre must be 5-100 chars")
    private String duration;

    private Integer directorId;


    public Integer getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer director) {
        this.directorId = director;
    }


}
