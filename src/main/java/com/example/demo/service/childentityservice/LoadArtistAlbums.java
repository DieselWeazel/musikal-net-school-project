package com.example.demo.service.childentityservice;

import com.example.demo.model.Album;
import com.example.demo.model.Artist;
import com.example.demo.model.dto.simple.SimpleAlbumDTO;
import com.example.demo.model.dto.simple.SimpleTrackDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoadArtistAlbums implements LoadChildEntities<Artist, SimpleAlbumDTO> {
    private final LoadChildEntities<Album, SimpleTrackDTO> loadTracksFromAlbum;

    public LoadArtistAlbums(LoadChildEntities<Album, SimpleTrackDTO> loadTracksFromAlbum) {
        this.loadTracksFromAlbum = loadTracksFromAlbum;
    }

    @Override
    public List<SimpleAlbumDTO> loadAllChildEntities(Artist artist) {
        return artist.getArtistAlbumList().stream().map(
                album -> new SimpleAlbumDTO(album.getEntityTitle(), loadTracksFromAlbum.loadAllChildEntities(album))
        ).collect(Collectors.toList());
    }
}
