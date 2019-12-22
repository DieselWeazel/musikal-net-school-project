package com.example.demo.model.dto;

import com.example.demo.model.dto.simple.SimpleAlbumOnlyDTO;
import com.example.demo.model.dto.simple.SimpleArtistOnlyDTO;
import com.example.demo.model.dto.simple.SimpleGenreDTO;
import com.example.demo.model.dto.simple.SimpleTrackDTO;

import java.util.List;

public class AlbumDTO extends EntityDTO {

    private List<SimpleTrackDTO> tracks;
    private SimpleArtistOnlyDTO artist;
    private SimpleGenreDTO genre;
    private String image;

    public AlbumDTO(String entityName, String description, List<SimpleTrackDTO> tracks, SimpleArtistOnlyDTO artist, SimpleGenreDTO genre, String image) {
        super(entityName, description);
        this.tracks = tracks;
        this.artist = artist;
        this.genre = genre;
        this.image = image;
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
}
