//package com.example.demo.model;
//
//import java.util.List;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name="description")
//public class Description {
//
//  @Id
//  @GeneratedValue
//  private Long id;
//
//  private String description;
//  @OneToOne
//  private Artist artist;
//  @OneToOne
//  private Album album;
//  @OneToOne
//  private Genre genre;
//  @OneToOne
//  private Track track;
//
//  public Description() {
//  }
//
//  public Description(String description) {
//    this.description = description;
//  }
//
//  public Long getId() {
//    return id;
//  }
//
//  public void setId(Long id) {
//    this.id = id;
//  }
//
//  public String getDescription() {
//    return description;
//  }
//
//  public void setDescription(String description) {
//    this.description = description;
//  }
//
//  public Artist getArtist() {
//    return artist;
//  }
//
//  public void setArtist(Artist artist) {
//    this.artist = artist;
//  }
//
//  public Album getAlbum() {
//    return album;
//  }
//
//  public void setAlbum(Album album) {
//    this.album = album;
//  }
//
//  public Genre getGenre() {
//    return genre;
//  }
//
//  public void setGenre(Genre genre) {
//    this.genre = genre;
//  }
//
//  public Track getTrack() {
//    return track;
//  }
//
//  public void setTrack(Track track) {
//    this.track = track;
//  }
//}
