package pe.edu.i202217005.exament2pacco.services;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import pe.edu.i202217005.exament2pacco.entity.Film;
import pe.edu.i202217005.exament2pacco.entity.FilmDTO;
import java.util.List;



public interface FilmService {
    @Cacheable("films")
    List<FilmDTO> getAllFilms();

    FilmDTO getFilmById(Integer id);

    @CacheEvict(value = "films", allEntries = true)
    Film saveFilm(Film film, Integer languageId);

    @CacheEvict(value = "films", allEntries = true)
    Film updateFilm(Film film);

    @CacheEvict(value = "films", allEntries = true)
    void deleteFilm(Integer id);
}