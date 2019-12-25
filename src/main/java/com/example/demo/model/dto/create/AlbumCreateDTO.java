package com.example.demo.model.dto.create;

import com.example.demo.model.dto.EntityDTO;
import com.example.demo.model.dto.simple.SimpleGenreDTO;
import com.example.demo.model.dto.simple.SimpleTrackDTO;

import java.util.List;

public class AlbumCreateDTO extends EntityDTO {

    private Long artistId;
    private Long genreId;
    private SimpleTrackDTO[] tracks;
    private String image;

    public AlbumCreateDTO(String entityName, String description, Long artistId, Long genreId, SimpleTrackDTO[] tracks, String image) {
        super(entityName, description);
        this.artistId = artistId;
        this.genreId = genreId;
        this.tracks = tracks;
        this.image = image;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public SimpleTrackDTO[] getTracks() {
        return tracks;
    }

    public void setTracks(SimpleTrackDTO[] tracks) {
        this.tracks = tracks;
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
        String albumTracksString = "tracks: ";
        for (SimpleTrackDTO track : tracks) {
            albumTracksString += track.getTrack() + ", ";
        }
        return "AlbumCreateDTO{" +
                "artistId=" + artistId +
                ", genreId=" + genreId + albumTracksString +
                '}';
    }
}
