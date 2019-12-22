package com.example.demo.model.dto;

import com.example.demo.model.dto.simple.SimpleAlbumOnlyDTO;
import com.example.demo.model.dto.simple.SimpleArtistOnlyDTO;
import com.example.demo.model.dto.simple.SimpleTrackDTO;

import java.util.List;

public class GenreDTO extends EntityDTO {

    private List<SimpleArtistOnlyDTO> artists;
    private List<SimpleAlbumOnlyDTO> albums;
    private List<SimpleTrackDTO> tracks;

    public GenreDTO(String entityName, String description, List<SimpleArtistOnlyDTO> artists, List<SimpleAlbumOnlyDTO> albumDTOList, List<SimpleTrackDTO> tracks) {
        super(entityName, description);
        this.artists = artists;
        this.albums = albumDTOList;
        this.tracks = tracks;
    }

    public List<SimpleArtistOnlyDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<SimpleArtistOnlyDTO> artists) {
        this.artists = artists;
    }

    public List<SimpleAlbumOnlyDTO> getAlbums() {
        return albums;
    }

    public void setAlbums(List<SimpleAlbumOnlyDTO> albums) {
        this.albums = albums;
    }

    public List<SimpleTrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<SimpleTrackDTO> tracks) {
        this.tracks = tracks;
    }
}
