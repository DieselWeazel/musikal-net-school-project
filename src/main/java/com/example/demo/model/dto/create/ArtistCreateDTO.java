package com.example.demo.model.dto.create;

import com.example.demo.model.dto.EntityDTO;
import com.example.demo.model.dto.simple.SimpleGenreDTO;

public class ArtistCreateDTO extends EntityDTO {

    private Long genreId;

    public ArtistCreateDTO(String entityName, String description, Long genreId) {
        super(entityName, description);
        this.genreId = genreId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
}
