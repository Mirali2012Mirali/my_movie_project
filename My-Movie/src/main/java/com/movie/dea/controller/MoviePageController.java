package com.movie.dea.controller;


import com.movie.dea.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
public class MoviePageController {
    private final MovieService movieService;

    public MoviePageController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("movies", movieService.getAllMovie());
        return "movies/list";
    }
}
