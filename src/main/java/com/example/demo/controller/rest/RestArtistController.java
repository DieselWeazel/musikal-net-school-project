package com.example.demo.controller.rest;

import com.example.demo.model.Artist;
import com.example.demo.model.dto.AlbumDTO;
import com.example.demo.model.dto.ArtistDTO;
import com.example.demo.service.AbstractMusicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/artist/")
public class RestArtistController {

    private final AbstractMusicService<Artist, ArtistDTO> artistService;

    public RestArtistController(AbstractMusicService<Artist, ArtistDTO> artistService) {
        this.artistService = artistService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<ArtistDTO> showAllArtists() {
        return artistService.loadAll();
    }
    @RequestMapping(value = "id", method = RequestMethod.GET)
    public ArtistDTO showArtist(Artist artist) {
        return artistService.findEntity(artist);
    }
}
