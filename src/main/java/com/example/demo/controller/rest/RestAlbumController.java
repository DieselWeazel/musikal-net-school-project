package com.example.demo.controller.rest;

import com.example.demo.model.Album;
import com.example.demo.model.dto.AlbumDTO;
import com.example.demo.service.AbstractMusicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/album/")
public class RestAlbumController {

    private final AbstractMusicService<Album, AlbumDTO> albumService;

    public RestAlbumController(AbstractMusicService<Album, AlbumDTO> albumService) {
        this.albumService = albumService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<AlbumDTO> showAllAlbums() {
        return albumService.loadAll();
    }

    @RequestMapping(value = "id", method = RequestMethod.GET)
    public AlbumDTO showAlbum(Album album) {
        return albumService.findEntity(album);
    }
}
