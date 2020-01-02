package com.example.demo.controller.rest;

import com.example.demo.model.Album;
import com.example.demo.model.dto.AlbumDTO;
import com.example.demo.model.dto.create.AlbumCreateDTO;
import com.example.demo.service.AbstractMusicService;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/album")
public class RestAlbumController {

  private final AbstractMusicService<Album, AlbumDTO, AlbumCreateDTO> albumService;

  public RestAlbumController(AbstractMusicService<Album, AlbumDTO, AlbumCreateDTO> albumService) {
    this.albumService = albumService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public List<AlbumDTO> showAllAlbums() {
    return albumService.loadAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public AlbumDTO showAlbum(Album album) {
    return albumService.findEntity(album);
  }

  @RequestMapping(value = "/filter", method = RequestMethod.POST)
  public List<AlbumDTO> showAlbumsByFilter(String filter) {
    return albumService.laodByFilter(filter);
  }

  @RequestMapping(value = "/genre", method = RequestMethod.POST)
  public List<AlbumDTO> showAlbumsByGenre(String genre) {
    return albumService.loadByGenre(genre);
  }

  @RequestMapping(method = RequestMethod.POST)
  public AlbumDTO createAlbum(@RequestBody AlbumCreateDTO albumCreateDTO) {
    return albumService.createEntity(albumCreateDTO);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public AlbumDTO updateAlbum(@PathVariable Long id, @RequestBody AlbumCreateDTO albumCreateDTO) {
    return albumService.updateEntity(id, albumCreateDTO);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public AlbumDTO deleteAlbum(@PathVariable Long id) {
    return albumService.removeEntity(id);
  }
}
