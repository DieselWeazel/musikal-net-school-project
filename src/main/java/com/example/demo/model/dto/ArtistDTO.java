package com.example.demo.model.dto;

import com.example.demo.model.dto.simple.SimpleAlbumDTO;
import com.example.demo.model.dto.simple.SimpleGenreDTO;

import java.util.List;

public class ArtistDTO extends EntityDTO {
    private List<SimpleAlbumDTO> albumList;
    private SimpleGenreDTO genre;
    private String image;

    public ArtistDTO(String entityName, String description, List<SimpleAlbumDTO> albumList, SimpleGenreDTO genre, String image) {
        super(entityName, description);
        this.albumList = albumList;
        this.genre = genre;
        this.image = image;
    }

    public List<SimpleAlbumDTO> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<SimpleAlbumDTO> albumList) {
        this.albumList = albumList;
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
