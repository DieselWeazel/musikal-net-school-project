package com.example.demo.service;

import com.example.demo.model.Genre;
import com.example.demo.model.dto.GenreDTO;
import com.example.demo.model.dto.simple.SimpleAlbumOnlyDTO;
import com.example.demo.model.dto.simple.SimpleArtistOnlyDTO;
import com.example.demo.model.dto.simple.SimpleGenreDTO;
import com.example.demo.model.dto.simple.SimpleTrackDTO;
import com.example.demo.repositories.GenreRepository;
import com.example.demo.service.childentityservice.LoadChildEntities;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements AbstractMusicService<Genre, GenreDTO, SimpleGenreDTO> {

    private final GenreRepository genreRepository;
    private final LoadChildEntities<Genre, SimpleArtistOnlyDTO> loadGenreTracksFromArtist;
    private final LoadChildEntities<Genre, SimpleAlbumOnlyDTO> loadGenreTracksFromAlbum;
    private final LoadChildEntities<Genre, SimpleTrackDTO> loadTracksFromGenre;

    public GenreServiceImpl(GenreRepository genreRepository, LoadChildEntities<Genre, SimpleArtistOnlyDTO> loadGenreTracksFromArtist, LoadChildEntities<Genre, SimpleAlbumOnlyDTO> loadGenreTracksFromAlbum, LoadChildEntities<Genre, SimpleTrackDTO> loadTracksFromGenre) {
        this.genreRepository = genreRepository;
        this.loadGenreTracksFromArtist = loadGenreTracksFromArtist;
        this.loadGenreTracksFromAlbum = loadGenreTracksFromAlbum;
        this.loadTracksFromGenre = loadTracksFromGenre;
    }

    @Override
    public List<GenreDTO> loadAll() {
        return genreRepository.findAll().stream().map(genre -> new GenreDTO(genre.getEntityTitle(), genre.getDescription(),
                loadGenreTracksFromArtist.loadAllChildEntities(genre), loadGenreTracksFromAlbum.loadAllChildEntities(genre),
                loadTracksFromGenre.loadAllChildEntities(genre))).collect(Collectors.toList());
    }

    @Override
    public List<GenreDTO> laodByFilter(String filterValue) {
        return null;
    }

    @Override
    public List<GenreDTO> loadByGenre(String genre) {
        return null;
    }

    @Override
    public GenreDTO findEntity(Genre genre) {
        Genre findGenre = genreRepository.findById(genre.getId()).orElseThrow(RuntimeException::new);
        if (findGenre != null) {
            return new GenreDTO(genre.getEntityTitle(), genre.getDescription(),
                    loadGenreTracksFromArtist.loadAllChildEntities(genre), loadGenreTracksFromAlbum.loadAllChildEntities(genre),
                    loadTracksFromGenre.loadAllChildEntities(genre));
        } else {
            return null;
        }
    }

    @Override
    public GenreDTO createEntity(SimpleGenreDTO simpleGenreDTO) {
        return null;
    }


    @Override
    public GenreDTO updateEntity(Long id, SimpleGenreDTO simpleGenreDTO) {
        return null;
    }

    @Override
    public GenreDTO removeEntity(Long id) {
        return null;
    }
}
