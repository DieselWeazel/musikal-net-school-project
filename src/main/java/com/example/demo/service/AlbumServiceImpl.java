package com.example.demo.service;

import com.example.demo.model.Album;
import com.example.demo.model.Artist;
import com.example.demo.model.Genre;
import com.example.demo.model.Track;
import com.example.demo.model.dto.create.AlbumCreateDTO;
import com.example.demo.model.dto.AlbumDTO;
import com.example.demo.model.dto.simple.SimpleArtistOnlyDTO;
import com.example.demo.model.dto.simple.SimpleGenreDTO;
import com.example.demo.model.dto.simple.SimpleTrackDTO;
import com.example.demo.repositories.AlbumRepository;
import com.example.demo.repositories.ArtistRepository;
import com.example.demo.repositories.GenreRepository;
import com.example.demo.service.childentityservice.LoadChildEntities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AlbumServiceImpl implements AbstractMusicService<Album, AlbumDTO, AlbumCreateDTO> {


    private final AlbumRepository albumRepository;
    private final LoadChildEntities<Album, SimpleTrackDTO> loadTracksFromAlbum;
//    private final AbstractMusicService<Track, TrackDTO> trackService;
    private final AlbumAddTrackService albumAddTrackService;
    private final GenreRepository genreRepository;
    private final ArtistRepository artistRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository, LoadChildEntities<Album, SimpleTrackDTO> loadTracksFromAlbum, AlbumAddTrackService albumAddTrackService, GenreRepository genreRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.loadTracksFromAlbum = loadTracksFromAlbum;
        this.albumAddTrackService = albumAddTrackService;
        this.genreRepository = genreRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<AlbumDTO> loadAll() {
        return albumRepository.findAll().stream().map(this::returnAlbumDTO).collect(Collectors.toList());
    }

    @Override
    public List<AlbumDTO> laodByFilter(String filterValue) {
        return albumRepository.findByEntityTitleContainingIgnoreCase(filterValue).stream().map(this::returnAlbumDTO).collect(Collectors.toList());
    }

    @Override
    public List<AlbumDTO> loadByGenre(String genre) {
        return albumRepository.findAll().stream().map(this::returnAlbumDTO).filter(album -> album.getGenre().getGenre().equalsIgnoreCase(genre)).collect(Collectors.toList());
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
    public AlbumDTO createEntity(AlbumCreateDTO albumCreateDTO) {
        Genre genre = genreRepository.findById(albumCreateDTO.getGenreId()).orElseThrow(RuntimeException::new);
        Artist artist = artistRepository.findById(albumCreateDTO.getArtistId()).orElseThrow(RuntimeException::new);
        log.info("Recieved = {}", albumCreateDTO.toString());
        Album newAlbum = new Album(albumCreateDTO.getName(), albumCreateDTO.getDescription(), albumAddTrackService.getTracksForAlbum(Arrays.asList(albumCreateDTO.getTracks()), genre, artist), artist, genre, albumCreateDTO.getImage());
        albumRepository.save(newAlbum);
        return returnAlbumDTO(newAlbum);
    }

    @Transactional
    @Override
    public AlbumDTO updateEntity(Long id, AlbumCreateDTO albumCreateDTO) {
        Album albumEdit = findAlbum(id);
        Genre genre = genreRepository.findById(albumCreateDTO.getGenreId()).orElseThrow(RuntimeException::new);
        Artist artist = artistRepository.findById(albumCreateDTO.getArtistId()).orElseThrow(RuntimeException::new);
        albumEdit.setEntityTitle(albumCreateDTO.getName());
        albumEdit.setDescription(albumCreateDTO.getDescription());
        albumEdit.getTrackList().clear();
        albumEdit.getTrackList().addAll((albumAddTrackService.getTracksForAlbum(Arrays.asList(albumCreateDTO.getTracks()), genre, artist)));
        albumEdit.setImage(albumCreateDTO.getImage());
        albumRepository.save(albumEdit);
        return returnAlbumDTO(albumEdit);
    }

    @Transactional
    @Override
    public AlbumDTO removeEntity(Long id) {
        Album deleteAlbum = findAlbum(id);
        deleteAlbum.getTrackList().clear();
        albumRepository.delete(deleteAlbum);
        return returnAlbumDTO(deleteAlbum);
    }

    private Album findAlbum(Long id) {
        return albumRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    private AlbumDTO returnAlbumDTO(Album album) {
        return new AlbumDTO(album.getEntityTitle(), album.getDescription(), album.getId(), loadTracksFromAlbum.loadAllChildEntities(album), new SimpleArtistOnlyDTO(album.getArtist().getEntityTitle()),
                new SimpleGenreDTO(album.getGenre().getEntityTitle()), album.getImage());
    }

}
