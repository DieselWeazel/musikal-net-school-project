package com.example.demo.model.dto.simple;

import java.util.List;

public class SimpleAlbumDTO {

    private String album;
    private List<SimpleTrackDTO> trackList;

    public SimpleAlbumDTO(String album) {
        this.album = album;
    }

    public SimpleAlbumDTO(String album, List<SimpleTrackDTO> trackList) {
        this.album = album;
        this.trackList = trackList;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public List<SimpleTrackDTO> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<SimpleTrackDTO> trackList) {
        this.trackList = trackList;
    }
}
