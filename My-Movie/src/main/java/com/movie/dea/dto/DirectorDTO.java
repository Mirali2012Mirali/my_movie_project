package com.movie.dea.dto;

public class DirectorDTO {
    private Integer id;
    private String name;
    private int movieCount;

    public DirectorDTO(Integer id, String name, int movieCount) {
        this.id = id;
        this.name = name;
        this.movieCount = movieCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMovieCount() {
        return movieCount;
    }

    public void setMovieCount(int movieCount) {
        this.movieCount = movieCount;
    }
}
