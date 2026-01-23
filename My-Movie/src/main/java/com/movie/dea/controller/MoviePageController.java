package com.movie.dea.controller;


import com.movie.dea.entity.Movie;
import com.movie.dea.service.MovieService;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
public class MoviePageController {
    private final MovieService movieService;

    public MoviePageController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
        public String list (
                @RequestParam(required = false) String title,
                @RequestParam(required = false) String genre,
                Model model
    ){
            model.addAttribute("movies", movieService.search(title, genre));
            model.addAttribute("title", title);
            model.addAttribute("genre", genre);
            return "movies/list";
        }

        @GetMapping("/new")
        public String form (Model model){
        model.addAttribute("movie", new Movie());
        return "movies/new";
        }

        @PostMapping
        public String save (@Valid @ModelAttribute Movie movie, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "movies/form";
        }


        movieService.createMovie(movie);
        return "redirect:/movies";
        }

        @GetMapping("/{id}/edit")
        public String edit (@PathVariable Integer id,Model model){
        Movie movie = movieService.getMovie(id);
        MovieForm form = new MovieForm();
        form.setId(movie.getId());
        form.setTitle(movie.getTitle());
        form.setGenre(movie.getGenre());
        form.setRating(movie.getRating());
        form.setDuration(movie.getDuration());
        form.setReleaseDate(movie.getReleaseDate());
        model.addAttribute("movieForm", form);
        return "movies/edit";
        }

        @PostMapping("/{id}/delete")
        public String delete (@PathVariable Integer id){
            movieService.deleteById(id);
            return "redirect:/movies";
        }
    }

