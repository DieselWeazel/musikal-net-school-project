package com.example.demo.model.dto.create;

import com.example.demo.model.dto.EntityDTO;

public class TrackCreateDTO extends EntityDTO {

    private Long artistId;
    private Long genreId;
    private Long albumId;

    public TrackCreateDTO(String entityName, String description, Long artistId, Long genreId, Long albumId) {
        super(entityName, description);
        this.artistId = artistId;
        this.genreId = genreId;
        this.albumId = albumId;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }
}
