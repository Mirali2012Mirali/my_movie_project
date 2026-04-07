package com.movie.dea.controller;


import com.movie.dea.dto.MovieDTO;
import com.movie.dea.dto.MovieForm;
import com.movie.dea.entity.Director;
import com.movie.dea.entity.Movie;
import com.movie.dea.repository.DirectorRepository;
import com.movie.dea.Interfaces.MovieService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/movies")
//@Tag(name="Movies", description = "Movie management API")
public class MoviePageController {
    private final MovieService movieService;
    private final DirectorRepository directorRepository;

    public MoviePageController(MovieService movieService, DirectorRepository directorRepository) {
        this.movieService = movieService;
        this.directorRepository = directorRepository;
    }

    @GetMapping
    public String list(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            Model model,
            Authentication authentication
    ) {

        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));


        if (page < 0) {
            page=0;
        }

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Page<MovieDTO> movies = movieService.searchPaginated(
                title,
                genre,
                page,
                size,
                sort
        );

        if (page >= movies.getTotalPages() && movies.getTotalPages() > 0) {
            page = movies.getTotalPages() - 1;
        }

        if (page >= movies.getTotalPages() && movies.getTotalPages() > 0) {
            page = movies.getTotalPages() - 1;
            movies = movieService.searchPaginated(
                    title,
                    genre,
                    page,
                    size,
                    sort
            );
        }

//        List<Movie> movies = movieService.getAllMovie();

        model.addAttribute("movies", movies.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", movies.getTotalPages());
        model.addAttribute("size", size);


        model.addAttribute("title", title);
        model.addAttribute("genre", genre);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        model.addAttribute("isAdmin", isAdmin);
        return "movies/list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("movieForm", new MovieForm());
        addDirectorSuggestions(model);
        return "movies/new";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("movieForm") MovieForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            addDirectorSuggestions(model);
            return form.getId() == null ? "movies/new" : "movies/edit";
        }

        Movie movie;

        if (form.getId()
                == null) {
            movie = new Movie();
        }
        else {
            movie = movieService.getMovie(form.getId());
        }

        movie.setTitle(form.getTitle());
        movie.setGenre(form.getGenre()) ;
        movie.setRating (form.getRating());
        movie.setDuration (form.getDuration()) ;
        movie.setReleaseDate(form.getReleaseDate()) ;
        String directorName = form.getDirectorName() == null ? "" : form.getDirectorName().trim();
        Director director = directorRepository.findByNameIgnoreCase(directorName)
                .orElseGet(() -> {
                    Director newDirector = new Director();
                    newDirector.setName(directorName);
                    return directorRepository.save(newDirector);
                });

        movie.setDirector(director);

        movieService.createMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        Movie movie = movieService.getMovie(id);
        MovieForm form = new MovieForm();
        form.setId(movie.getId());
        form.setTitle(movie.getTitle());
        form.setGenre(movie.getGenre());
        form.setRating(movie.getRating());
        form.setDuration(movie.getDuration());
        form.setReleaseDate(movie.getReleaseDate());
        if (movie.getDirector() != null) {
            form.setDirectorName(movie.getDirector().getName());
        }

        model.addAttribute("movieForm", form);
        addDirectorSuggestions(model);
        return "movies/edit";
    }

    private void addDirectorSuggestions(Model model) {
        List<String> directorNames = directorRepository.findAll().stream()
                .map(Director::getName)
                .filter(name -> name != null && !name.isBlank())
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .distinct()
                .toList();
        model.addAttribute("directorNames", directorNames);
    }

    @PostMapping("/delete/{id}")
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