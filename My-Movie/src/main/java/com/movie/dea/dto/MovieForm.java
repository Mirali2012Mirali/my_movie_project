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

    @NotBlank(message = "{error.notblank}")
    @Size(min = 5, max = 100, message = "{error.size}}")
    private String title;
    @NotBlank(message = "Genre is required!")
    @Size(min = 5, max = 100, message = "{error.size}}")
    private String genre;


    @NotBlank(message = "{error.notblank}")
    private LocalDate releaseDate;

    @NotBlank(message = "{error.notblank}")
    @DecimalMin(value = "1.0", message = "Rating must be at least 0")
    @DecimalMax(value = "10.0", message = "Rating must be at most 10")

    private Double rating;

    @NotBlank(message = "{error.notblank}")
    @Size(min = 2, max = 3, message = "{error.size}}")
    private String duration;
    @NotBlank(message = "{error.notblank}")
    private Integer directorId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
