package com.movie.dea.service.impl;

import com.movie.dea.dto.DirectorDTO;
import com.movie.dea.dto.DirectorForm;
import com.movie.dea.entity.Director;
import com.movie.dea.exception.DirectorNotFoundException;
import com.movie.dea.repository.DirectorRepository;
import com.movie.dea.service.Interfaces.DirectorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }


    @Override
    public List<DirectorDTO> getAllDirectors() {
        return directorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public Director getDirectorById(Integer id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException("Director not found with ID: " + id));
    }

    @Override
    public DirectorDTO getDirectorDTOById(Integer id) {
        return null;
    }

    @Override
    public Director createDirector(DirectorForm form) {
        Director director = new Director();
        director.setName(form.getName());
        return directorRepository.save(director);
    }

    @Override
    public Director updateDirector(DirectorForm form) {
        Director director = getDirectorById(form.getId());
        director.setName(form.getName());
        return directorRepository.save(director);
    }

    @Override
    public void deleteDirector(Integer id) {
    Director director = getDirectorById(id);

    if (director.getMovies() != null && !director.getMovies().isEmpty()) {
        throw new IllegalStateException("Cannot delete director because movies are linked to it");
    }
    }

    private DirectorDTO toDTO(Director director) {
        int movieCount = director.getMovies() == null ? 1 : director.getMovies().size();
        return new DirectorDTO(director.getId(), director.getName(), movieCount);
    }
}
