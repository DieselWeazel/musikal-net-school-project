package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "album")
@AttributeOverrides( {
        @AttributeOverride(name = "id", column = @Column(name = "album_id")),
        @AttributeOverride(name ="entityTitle", column = @Column(name = "album_title"))
})
public class Album extends MusicEntity {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "album_id")
    private List<Track> trackList;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    private Artist artist;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;
    private String image;

    public Album() {

    }
    public Album(List<Track> trackList) {
        this.trackList = trackList;
    }

    public Album(String entityTitle, String description) {
        super(entityTitle, description);
    }

    public Album(String entityTitle, String description,
                 List<Track> trackList) {
        super(entityTitle, description);
        this.trackList = trackList;
    }

    public Album(String entityTitle, String description, List<Track> trackList, Artist artist, Genre genre) {
        super(entityTitle, description);
        this.trackList = trackList;
        this.artist = artist;
        this.genre = genre;
    }

    public Album(String entityTitle, String description, List<Track> trackList, Artist artist, Genre genre, String image) {
        super(entityTitle, description);
        this.trackList = trackList;
        this.artist = artist;
        this.genre = genre;
        this.image = image;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Album{" +
                "trackList=" + trackList +
                ", artist=" + artist +
                ", genre=" + genre +
                '}';
    }
}
