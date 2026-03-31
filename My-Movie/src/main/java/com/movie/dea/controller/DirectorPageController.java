package com.movie.dea.controller;

import com.movie.dea.repository.DirectorRepository;
import com.movie.dea.service.Interfaces.DirectorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/directors")
public class DirectorPageController {

    private final DirectorService directorService;

    public DirectorPageController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("directors", directorService.getAllDirectors());
        return "directors/list";
    }
}
