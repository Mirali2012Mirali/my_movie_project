package com.movie.dea.service.Interfaces;

import com.movie.dea.dto.DirectorDTO;
import com.movie.dea.dto.DirectorForm;
import com.movie.dea.entity.Director;

import java.util.List;

public interface DirectorService {
    List<DirectorDTO> getAllDirectors();

    Director getDirectorById(Integer id);

    DirectorDTO getDirectorDTOById(Integer id);

    Director createDirector(DirectorForm form);

    Director updateDirector(DirectorForm form);

    void deleteDirector(Integer id);
}
