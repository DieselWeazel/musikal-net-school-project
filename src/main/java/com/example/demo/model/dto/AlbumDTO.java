package com.example.demo.model.dto;

import com.example.demo.model.dto.simple.SimpleArtistOnlyDTO;
import com.example.demo.model.dto.simple.SimpleGenreDTO;
import com.example.demo.model.dto.simple.SimpleTrackDTO;
import java.util.List;

public class AlbumDTO extends EntityDTO {

  private Long id;
  private List<SimpleTrackDTO> tracks;
  private Long artistId;
  private Long genreId;
  private SimpleArtistOnlyDTO artist;
  private SimpleGenreDTO genre;
  private String image;

  public AlbumDTO(
      String entityName,
      String description,
      Long id,
      List<SimpleTrackDTO> tracks,
      Long artistId,
      Long genreId,
      SimpleArtistOnlyDTO artist,
      SimpleGenreDTO genre,
      String image) {
    super(entityName, description);
    this.id = id;
    this.tracks = tracks;
    this.artistId = artistId;
    this.genreId = genreId;
    this.artist = artist;
    this.genre = genre;
    this.image = image;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<SimpleTrackDTO> getTracks() {
    return tracks;
  }

  public void setTracks(List<SimpleTrackDTO> tracks) {
    this.tracks = tracks;
  }

  public SimpleArtistOnlyDTO getArtist() {
    return artist;
  }

  public void setArtist(SimpleArtistOnlyDTO artist) {
    this.artist = artist;
  }

  public SimpleGenreDTO getGenre() {
    return genre;
  }

  public void setGenre(SimpleGenreDTO genre) {
    this.genre = genre;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Long getArtistId() {
    return artistId;
  }

  public void setArtistId(Long artistId) {
    this.artistId = artistId;
  }

  public Long getGenreId() {
    return genreId;
  }

  public void setGenreId(Long genreId) {
    this.genreId = genreId;
  }
}
