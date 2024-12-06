package pe.edu.i202217005.exament2pacco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.i202217005.exament2pacco.entity.Film;
import pe.edu.i202217005.exament2pacco.entity.FilmDTO;
import pe.edu.i202217005.exament2pacco.services.FilmService;
import pe.edu.i202217005.exament2pacco.services.Languageservice;

@Controller
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;
    private final Languageservice languageservice;

    public FilmController(FilmService filmService, Languageservice languageservice) {
        this.filmService = filmService;
        this.languageservice = languageservice;
    }

    @GetMapping
    public String listFilms(Model model) {
        model.addAttribute("films", filmService.getAllFilms());
        return "filmlist";
    }

    @GetMapping("/{id}")
    public String filmDetails(@PathVariable Integer id, Model model) {
        FilmDTO film = filmService.getFilmById(id);
        model.addAttribute("film", film);
        return "filmdetails";
    }

    @GetMapping("/new")
    public String newFilmForm(Model model) {
        model.addAttribute("film", new Film());
        model.addAttribute("languages", languageservice.getAllLanguages());
        return "filmnew";
    }

    @PostMapping("/save")
    public String saveFilm(@ModelAttribute Film film, @RequestParam Integer languageId) {
        filmService.saveFilm(film, languageId);
        return "redirect:/films";
    }

    @GetMapping("/edit/{id}")
    public String editFilmForm(@PathVariable Integer id, Model model) {
        FilmDTO film = filmService.getFilmById(id);
        model.addAttribute("film", film);
        return "filmform";
    }

    @PostMapping("/update")
    public String updateFilm(@ModelAttribute Film film) {
        filmService.updateFilm(film);
        return "redirect:/films";
    }

    @GetMapping("/delete/{id}")
    public String deleteFilm(@PathVariable Integer id) {
        filmService.deleteFilm(id);
        return "redirect:/films";
    }
}