package com.movie.dea.controller;


import com.movie.dea.dto.MovieForm;
import com.movie.dea.entity.Movie;
import com.movie.dea.service.MovieService;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MoviePageController {
    private final MovieService movieService;

    public MoviePageController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public String list(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            Model model
    ) {

        Sort sort = direction.equals("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        List<Movie> movies = movieService.getAllMovie();

        model.addAttribute("movies", movieService.search(title, genre));
        model.addAttribute("title", title);
        model.addAttribute("genre", genre);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);
        return "movies/list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("movie", new Movie());
        return "movies/new";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute Movie movie, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "movies/form";
        }


        movieService.createMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        Movie movie = movieService.getMovie(id);
        MovieForm form = new MovieForm();
        movie.setId(form.getId());
        movie.setTitle(form.getTitle());
        movie.setGenre(form.getGenre());
        movie.setRating(form.getRating());
        movie.setDuration(form.getDuration());
        movie.setReleaseDate(form.getReleaseDate());
        model.addAttribute("movieForm", form);
        return "movies/edit";
    }

    @PostMapping("/{id}/delete")
    public String delete(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {
        try {
            movieService.deleteById(id);
            redirectAttributes.addFlashAttribute(
                    "success",
                    "Movie delete successfully"
            );
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage()
            );

        }
            return "redirect:/movies";
        }

}

