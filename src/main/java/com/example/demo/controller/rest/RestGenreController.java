package com.example.demo.controller.rest;

import com.example.demo.model.Genre;
import com.example.demo.model.dto.GenreDTO;
import com.example.demo.model.dto.simple.SimpleGenreDTO;
import com.example.demo.service.AbstractMusicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/genre/")
public class RestGenreController {

    private final AbstractMusicService<Genre, GenreDTO, SimpleGenreDTO> genreService;

    public RestGenreController(AbstractMusicService<Genre, GenreDTO, SimpleGenreDTO> genreService) {
        this.genreService = genreService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GenreDTO> showAllGenres() {
        return genreService.loadAll();
    }

    @RequestMapping(value = "id", method = RequestMethod.GET)
    public GenreDTO showGenre(Genre genre) {
        return genreService.findEntity(genre);
    }
}
