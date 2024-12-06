package pe.edu.i202217005.exament2pacco.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.i202217005.exament2pacco.entity.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {
}