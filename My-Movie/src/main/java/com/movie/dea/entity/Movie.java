package com.movie.dea.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String genre;
    private LocalDate releaseDate;
    private Double rating;
    private String duration;
    private Pageable pageable;


    public Movie() {

    }


    public Movie(Integer id, String title, String genre, String duration, Double rating, LocalDate releaseDate, Pageable pageable) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.pageable = pageable;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating){
        this.rating = rating;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public Integer getId(){return id;}

    public void setId(Integer id){this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getGenre() {return genre;}

    public void setGenre(String genre) {this.genre = genre;}

//    @OneToOne(mappedBy = "movies", cascade = CascadeType.ALL)
//    private MovieDetails movieDetails;
}
