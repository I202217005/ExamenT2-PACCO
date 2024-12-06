package pe.edu.i202217005.exament2pacco.services.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import pe.edu.i202217005.exament2pacco.entity.*;
import pe.edu.i202217005.exament2pacco.repository.FilmRepository;
import pe.edu.i202217005.exament2pacco.repository.LanguageRepository;
import pe.edu.i202217005.exament2pacco.services.FilmService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final LanguageRepository languageRepository;
    private final FilmCategoryRepository filmCategoryRepository;

    public FilmServiceImpl(FilmRepository filmRepository,
                           LanguageRepository languageRepository,
                           FilmCategoryRepository filmCategoryRepository) {
        this.filmRepository = filmRepository;
        this.languageRepository = languageRepository;
        this.filmCategoryRepository = filmCategoryRepository;
    }

    @Override
    public List<FilmDTO> getAllFilms() {
        return filmRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FilmDTO getFilmById(Integer id) {
        return filmRepository.findById(Long.valueOf(id))
                .map(this::convertToDTO)
                .orElse(null);
    }


    @Override
    public void deleteFilm(Integer id) {
        filmRepository.deleteById(Long.valueOf(id));
    }



    @Override
    public Film saveFilm(Film film, Integer languageId) {
        Language language = languageRepository.findById(languageId)
                .orElseThrow(() -> new RuntimeException("Language not found"));

        film.setLanguage(language);
        film.setLast_update(new Date());


        return filmRepository.save(film);
    }




    @CacheEvict(value = "films", allEntries = true)
    @Override
    public Film updateFilm(Film film) {
        Film existingFilm = filmRepository.findById(film.getFilm_id())
                .orElseThrow(() -> new RuntimeException("Film not found"));

        existingFilm.setTitle(film.getTitle());
        existingFilm.setDescription(film.getDescription());
        existingFilm.setRelease_year(film.getRelease_year());
        existingFilm.setLength(film.getLength());
        existingFilm.setLast_update(new Date());

        return filmRepository.save(existingFilm);
    }







    private FilmDTO convertToDTO(Film film) {
        FilmDTO dto = new FilmDTO();
        dto.setFilm_id(film.getFilm_id());
        dto.setTitle(film.getTitle());
        dto.setDescription(film.getDescription());
        dto.setRelease_year(film.getRelease_year());
        dto.setLanguage_name(film.getLanguage() != null ? film.getLanguage().getName() : null);
        dto.setRental_duration(film.getRental_duration());
        dto.setRental_rate(film.getRental_rate());
        return dto;
    }
}