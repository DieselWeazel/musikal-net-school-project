package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "track")
@AttributeOverrides( {
        @AttributeOverride(name = "id", column = @Column(name = "track_id")),
        @AttributeOverride(name ="entityTitle", column = @Column(name = "track_title"))
})
public class Track extends MusicEntity {
//    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id")
    private Album album;
//    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    public Track() {

    }
    public Track(String entityTitle, String description) {
        super(entityTitle, description);
    }

    public Track(String entityTitle, String description, Genre genre, Album album) {
        super(entityTitle, description);
        this.genre = genre;
        this.album = album;
    }

    public Track(String entityTitle, String description, Genre genre, Artist artist) {
        super(entityTitle, description);
        this.genre = genre;
        this.artist = artist;
    }

    public Track(String entityTitle, String description, Genre genre, Album album, Artist artist) {
        super(entityTitle, description);
        this.genre = genre;
        this.album = album;
        this.artist = artist;
    }

    public Track(String track, String correctNumeration, Optional<Genre> byId, Optional<Artist> byId1) {
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Track{" +
                "genre=" + genre +
                ", album=" + album +
                '}';
    }
}
