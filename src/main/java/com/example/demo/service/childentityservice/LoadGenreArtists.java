package com.example.demo.service.childentityservice;

import com.example.demo.model.Genre;
import com.example.demo.model.dto.simple.SimpleAlbumOnlyDTO;
import com.example.demo.model.dto.simple.SimpleArtistOnlyDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoadGenreArtists implements LoadChildEntities<Genre, SimpleArtistOnlyDTO>  {
    @Override
    public List<SimpleArtistOnlyDTO> loadAllChildEntities(Genre genre) {
        return genre.getArtistList().stream().map(
                artist -> new SimpleArtistOnlyDTO(artist.getEntityTitle())
        ).collect(Collectors.toList());
    }
}
