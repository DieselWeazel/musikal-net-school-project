package com.example.demo.controller;

import com.example.demo.model.Album;
import com.example.demo.model.Artist;
import com.example.demo.repositories.AlbumRepository;
import com.example.demo.repositories.ArtistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@RequestMapping("/")
@Controller
public class ViewController {

  private final ArtistRepository artistRepository;
  private final AlbumRepository albumRepository;

  public ViewController(ArtistRepository artistRepository, AlbumRepository albumRepository) {
    this.artistRepository = artistRepository;
    this.albumRepository = albumRepository;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String index() {
    return "home";
  }

  @RequestMapping(value = "home", method = RequestMethod.GET)
  public String home() {
    return "home";
  }

  @RequestMapping(value = "artist", method = RequestMethod.GET)
  public String artist() {
    return "artist";
  }

  @RequestMapping(value = "add-artist", method = RequestMethod.GET)
  public String addArtist() {
    return "add-artist";
  }

  @RequestMapping(value = "add-album", method = RequestMethod.GET)
  public String addAlbum() {
    return "add-album";
  }

  @RequestMapping(value = "about", method = RequestMethod.GET)
  public String about() {
    return "about";
  }

  @RequestMapping(value = "contact", method = RequestMethod.GET)
  public String contact() {
    return "contact";
  }

  @GetMapping(value = "/view/artist/{id}")
  public String showArtist(@PathVariable("id") Long id, Model model) {
    Artist artist = artistRepository.findById(id).orElseThrow(RuntimeException::new);
    model.addAttribute("artist", artist);
    return "show-artist";
  }

  @GetMapping(value = "/view/album/{id}")
  public String showAlbum(@PathVariable("id") Long id, Model model) {
    Album album = albumRepository.findById(id).orElseThrow(RuntimeException::new);
    model.addAttribute("album", album);
    return "show-album";
  }
}
