package com.movie.dea.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DirectorForm {

    private Integer id;

    @NotBlank(message = "Director name is required!")
    @Size(min = 2, max = 100, message = "Director name must be between 2 and 100!")
    private String name;

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
}
