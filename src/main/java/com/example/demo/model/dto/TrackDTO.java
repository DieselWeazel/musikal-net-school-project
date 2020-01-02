package com.example.demo.model.dto;

import com.example.demo.model.dto.simple.SimpleAlbumOnlyDTO;
import com.example.demo.model.dto.simple.SimpleArtistOnlyDTO;
import com.example.demo.model.dto.simple.SimpleGenreDTO;

public class TrackDTO extends EntityDTO {

  private Long trackId;
  private SimpleArtistOnlyDTO artist;
  private SimpleAlbumOnlyDTO album;
  private SimpleGenreDTO genre;

  public TrackDTO(
      String entityName,
      String description,
      Long trackId,
      SimpleArtistOnlyDTO artist,
      SimpleAlbumOnlyDTO album,
      SimpleGenreDTO genre) {
    super(entityName, description);
    this.trackId = trackId;
    this.artist = artist;
    this.album = album;
    this.genre = genre;
  }

  public Long getTrackId() {
    return trackId;
  }

  public void setTrackId(Long trackId) {
    this.trackId = trackId;
  }

  public SimpleAlbumOnlyDTO getAlbum() {
    return album;
  }

  public void setAlbum(SimpleAlbumOnlyDTO album) {
    this.album = album;
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
}
