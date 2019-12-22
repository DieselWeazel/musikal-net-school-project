package com.example.demo.model.dto.simple;

public class SimpleAlbumOnlyDTO {

    private String album;

    public SimpleAlbumOnlyDTO(String album) {
        this.album = album;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
