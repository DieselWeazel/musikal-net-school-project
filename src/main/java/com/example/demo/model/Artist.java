package com.example.demo.model;

import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "artist")
@AttributeOverrides({
  @AttributeOverride(name = "id", column = @Column(name = "artist_id")),
  @AttributeOverride(name = "entityTitle", column = @Column(name = "artist_title"))
})
public class Artist extends MusicEntity {

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "artist_id")
  private List<Album> artistAlbumList;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "artist_id")
  private List<Track> artistTrackList;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "genre_id")
  private Genre genre;

  private String image;

  public Artist() {}

  public Artist(String entityTitle, String description) {
    super(entityTitle, description);
  }

  public Artist(String entityTitle, String description, Genre genre, String image) {
    super(entityTitle, description);
    this.genre = genre;
    this.image = image;
  }

  public Artist(String entityTitle, String description, List<Album> artistAlbumList) {
    super(entityTitle, description);
    this.artistAlbumList = artistAlbumList;
  }

  public Artist(
      String entityTitle,
      String description,
      List<Album> artistAlbumList,
      List<Track> artistTrackList,
      Genre genre) {
    super(entityTitle, description);
    this.artistAlbumList = artistAlbumList;
    this.artistTrackList = artistTrackList;
    this.genre = genre;
  }

  public Artist(
      String entityTitle,
      String description,
      List<Album> artistAlbumList,
      List<Track> artistTrackList,
      Genre genre,
      String image) {
    super(entityTitle, description);
    this.artistAlbumList = artistAlbumList;
    this.artistTrackList = artistTrackList;
    this.genre = genre;
    this.image = image;
  }

  public List<Album> getArtistAlbumList() {
    return artistAlbumList;
  }

  public void setArtistAlbumList(List<Album> artistAlbumList) {
    this.artistAlbumList = artistAlbumList;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public List<Track> getArtistTrackList() {
    return artistTrackList;
  }

  public void setArtistTrackList(List<Track> artistTrackList) {
    this.artistTrackList = artistTrackList;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return "Artist{" + "artistAlbumList=" + artistAlbumList + ", genre=" + genre + '}';
  }
}
