package com.example.demo.model.dto.create;

import com.example.demo.model.dto.EntityDTO;
import com.example.demo.model.dto.simple.SimpleGenreDTO;

public class ArtistCreateDTO extends EntityDTO {

    private Long genreId;
    private String image;

    public ArtistCreateDTO(String entityName, String description, Long genreId, String image) {
        super(entityName, description);
        this.genreId = genreId;
        this.image = image;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ArtistCreateDTO{" +
                "genreId=" + genreId +
                '}';
    }
}
