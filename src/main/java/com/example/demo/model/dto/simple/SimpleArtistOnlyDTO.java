package com.example.demo.model.dto.simple;

public class SimpleArtistOnlyDTO {

    private String artist;

    public SimpleArtistOnlyDTO(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
