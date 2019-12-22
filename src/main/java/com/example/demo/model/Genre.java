package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genre")
@AttributeOverrides( {
        @AttributeOverride(name = "id", column = @Column(name = "genre_id")),
        @AttributeOverride(name ="entityTitle", column = @Column(name = "genre_title"))
})
public class Genre extends MusicEntity {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private List<Artist> artistList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private List<Album> albumList;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private List<Track> trackList;

    public Genre() {

    }
    public Genre(String entityTitle, String description) {
        super(entityTitle, description);
    }

    public List<Artist> getArtistList() {
        return artistList;
    }

    public void setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "artistList=" + artistList +
                ", albumList=" + albumList +
                ", trackList=" + trackList +
                '}';
    }
}
