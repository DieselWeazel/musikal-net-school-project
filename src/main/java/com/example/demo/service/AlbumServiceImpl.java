package com.example.demo.service;

import com.example.demo.model.Album;
import com.example.demo.model.dto.TrackDTO;
import com.example.demo.model.dto.simple.SimpleAlbumOnlyDTO;
import com.example.demo.model.dto.AlbumDTO;
import com.example.demo.model.dto.simple.SimpleArtistOnlyDTO;
import com.example.demo.model.dto.simple.SimpleGenreDTO;
import com.example.demo.model.dto.simple.SimpleTrackDTO;
import com.example.demo.repositories.AlbumRepository;
import com.example.demo.service.childentityservice.LoadChildEntities;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AbstractMusicService<Album, AlbumDTO> {

    private final AlbumRepository albumRepository;
    private final LoadChildEntities<Album, SimpleTrackDTO> loadTracksFromAlbum;

    public AlbumServiceImpl(AlbumRepository albumRepository, LoadChildEntities<Album, SimpleTrackDTO> loadTracksFromAlbum) {
        this.albumRepository = albumRepository;
        this.loadTracksFromAlbum = loadTracksFromAlbum;
    }

    @Override
    public List<AlbumDTO> loadAll() {
        return albumRepository.findAll().stream().map(this::returnAlbumDTO).collect(Collectors.toList());
    }

    @Override
    public AlbumDTO findEntity(Album album) {
        Album findAlbum = albumRepository.findById(album.getId()).orElseThrow(RuntimeException::new);
        if (findAlbum != null) {
            return returnAlbumDTO(findAlbum);
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public AlbumDTO createEntity(Album album) {
        albumRepository.save(album);
        return returnAlbumDTO(album);
    }

    @Transactional
    @Override
    public AlbumDTO updateEntity(Long id, Album album) {
        Album albumEdit = findAlbum(id);
        albumEdit.setArtist(album.getArtist());
        albumEdit.setGenre(album.getGenre());
        albumEdit.setDescription(album.getDescription());
        albumEdit.setEntityTitle(album.getEntityTitle());
        albumEdit.setTrackList(album.getTrackList());
        albumRepository.save(albumEdit);
        return returnAlbumDTO(album);
    }

    @Transactional
    @Override
    public AlbumDTO removeEntity(Long id) {
        Album deleteAlbum = findAlbum(id);
        albumRepository.delete(deleteAlbum);
        return returnAlbumDTO(deleteAlbum);
    }

    private Album findAlbum(Long id) {
        return albumRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    private AlbumDTO returnAlbumDTO(Album album) {
        return new AlbumDTO(album.getEntityTitle(), album.getDescription(), loadTracksFromAlbum.loadAllChildEntities(album), new SimpleArtistOnlyDTO(album.getArtist().getEntityTitle()),
                new SimpleGenreDTO(album.getGenre().getEntityTitle()), album.getImage());
    }

}
