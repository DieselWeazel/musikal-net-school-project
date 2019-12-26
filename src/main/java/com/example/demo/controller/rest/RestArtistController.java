package com.example.demo.controller.rest;

import com.example.demo.model.Album;
import com.example.demo.model.Artist;
import com.example.demo.model.dto.AlbumDTO;
import com.example.demo.model.dto.ArtistDTO;
import com.example.demo.model.dto.create.ArtistCreateDTO;
import com.example.demo.service.AbstractMusicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/artist")
public class RestArtistController {

    private final AbstractMusicService<Artist, ArtistDTO, ArtistCreateDTO> artistService;

    public RestArtistController(AbstractMusicService<Artist, ArtistDTO, ArtistCreateDTO> artistService) {
        this.artistService = artistService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<ArtistDTO> showAllArtists() {
        return artistService.loadAll();
    }
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ArtistDTO showArtist(Artist artist) {
        return artistService.findEntity(artist);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public List<ArtistDTO> showAlbumsByFilter(String genre) {
        return artistService.laodByFilter(genre);
    }

    @RequestMapping(value = "/genre", method = RequestMethod.POST)
    public List<ArtistDTO> showArtistsByGenre(String genre) {
        return artistService.loadByGenre(genre);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ArtistDTO createArtist(@RequestBody Artist artist) {
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ArtistDTO updateArtist(@PathVariable Long id, @RequestBody Artist artist) {
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ArtistDTO deleteArtist(@PathVariable Long id) {
        return artistService.removeEntity(id);
    }


}
