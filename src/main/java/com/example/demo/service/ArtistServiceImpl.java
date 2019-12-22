package com.example.demo.service;

import com.example.demo.model.Artist;
import com.example.demo.model.dto.ArtistDTO;
import com.example.demo.model.dto.simple.SimpleAlbumDTO;
import com.example.demo.model.dto.simple.SimpleGenreDTO;
import com.example.demo.repositories.ArtistRepository;
import com.example.demo.service.childentityservice.LoadChildEntities;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements AbstractMusicService<Artist, ArtistDTO> {

    private final ArtistRepository artistRepository;
    private final LoadChildEntities<Artist, SimpleAlbumDTO> loadArtistAlbums;

    public ArtistServiceImpl(ArtistRepository artistRepository, LoadChildEntities<Artist, SimpleAlbumDTO> loadArtistAlbums) {
        this.artistRepository = artistRepository;
        this.loadArtistAlbums = loadArtistAlbums;
    }

    @Override
    public List<ArtistDTO> loadAll() {
        return artistRepository.findAll().stream().map(this::returnArtistDTO).collect(Collectors.toList());
    }

    @Override
    public ArtistDTO findEntity(Artist artist) {
        Artist findArtist = artistRepository.findById(artist.getId()).orElseThrow(RuntimeException::new);
        if (findArtist != null) {
            return returnArtistDTO(findArtist);
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public ArtistDTO createEntity(Artist artist) {
        artistRepository.save(artist);
        return returnArtistDTO(artist);
    }

    @Transactional
    @Override
    public ArtistDTO updateEntity(Long id, Artist artist) {
        Artist updateArtist = findArtist(id);
        updateArtist.setEntityTitle(artist.getEntityTitle());
        updateArtist.setDescription(artist.getDescription());
        updateArtist.setArtistTrackList(artist.getArtistTrackList());
        updateArtist.setArtistAlbumList(artist.getArtistAlbumList());
        updateArtist.setGenre(artist.getGenre());
        artistRepository.save(updateArtist);
        return returnArtistDTO(artist);
    }
    @Transactional
    @Override
    public ArtistDTO removeEntity(Long id) {
        Artist deleteArtist = findArtist(id);
        artistRepository.delete(deleteArtist);
        return returnArtistDTO(deleteArtist);
    }

    private Artist findArtist(Long id) {
        return artistRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    private ArtistDTO returnArtistDTO(Artist artist) {
        return new ArtistDTO(artist.getEntityTitle(), artist.getDescription(),
                loadArtistAlbums.loadAllChildEntities(artist), new SimpleGenreDTO(artist.getGenre().getEntityTitle()),
                artist.getImage()
        );
    }

}
