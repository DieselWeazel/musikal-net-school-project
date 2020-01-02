package com.example.demo.service;

import com.example.demo.model.Album;
import com.example.demo.model.Artist;
import com.example.demo.model.Genre;
import com.example.demo.model.dto.ArtistDTO;
import com.example.demo.model.dto.create.ArtistCreateDTO;
import com.example.demo.model.dto.simple.SimpleAlbumDTO;
import com.example.demo.model.dto.simple.SimpleGenreDTO;
import com.example.demo.repositories.AlbumRepository;
import com.example.demo.repositories.ArtistRepository;
import com.example.demo.repositories.GenreRepository;
import com.example.demo.service.childentityservice.LoadChildEntities;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ArtistServiceImpl implements AbstractMusicService<Artist, ArtistDTO, ArtistCreateDTO> {

  private final ArtistRepository artistRepository;
  private final GenreRepository genreRepository;
  private final AlbumRepository albumRepository;
  private final LoadChildEntities<Artist, SimpleAlbumDTO> loadArtistAlbums;

  public ArtistServiceImpl(
      ArtistRepository artistRepository,
      GenreRepository genreRepository,
      AlbumRepository albumRepository,
      LoadChildEntities<Artist, SimpleAlbumDTO> loadArtistAlbums) {
    this.artistRepository = artistRepository;
    this.genreRepository = genreRepository;
    this.albumRepository = albumRepository;
    this.loadArtistAlbums = loadArtistAlbums;
  }

  @Override
  public List<ArtistDTO> loadAll() {
    return artistRepository.findAll().stream()
        .map(this::returnArtistDTO)
        .collect(Collectors.toList());
  }

  @Override
  public List<ArtistDTO> laodByFilter(String filterValue) {
    return artistRepository.findByEntityTitleContainingIgnoreCase(filterValue).stream()
        .map(this::returnArtistDTO)
        .collect(Collectors.toList());
  }

  @Override
  public List<ArtistDTO> loadByGenre(String genre) {
    return artistRepository.findAll().stream()
        .map(this::returnArtistDTO)
        .filter(artist -> artist.getGenre().getGenre().equalsIgnoreCase(genre))
        .collect(Collectors.toList());
  }

  @Override
  public ArtistDTO findEntity(Artist artist) {
    Artist findArtist =
        artistRepository.findById(artist.getId()).orElseThrow(RuntimeException::new);
    if (findArtist != null) {
      return returnArtistDTO(findArtist);
    } else {
      return null;
    }
  }

  @Transactional
  @Override
  public ArtistDTO createEntity(ArtistCreateDTO artistCreateDTO) {
    Genre genre =
        genreRepository.findById(artistCreateDTO.getGenreId()).orElseThrow(RuntimeException::new);
    log.info("Recieved = {}", artistCreateDTO.toString());
    Artist newArtist =
        new Artist(
            artistCreateDTO.getName(),
            artistCreateDTO.getDescription(),
            genre,
            artistCreateDTO.getImage());
    log.info("Created Artist = {}", newArtist.getEntityTitle() + newArtist.getDescription());
    artistRepository.save(newArtist);
    return new ArtistDTO(
        artistCreateDTO.getName(),
        artistCreateDTO.getDescription(),
        newArtist.getId(),
        new SimpleGenreDTO(genre.getEntityTitle()),
        artistCreateDTO.getImage());
  }

  @Transactional
  @Override
  public ArtistDTO updateEntity(Long id, ArtistCreateDTO artistCreateDTO) {
    Artist updateArtist = findArtist(id);
    updateArtist.setEntityTitle(artistCreateDTO.getName());
    updateArtist.setDescription(artistCreateDTO.getDescription());
    artistRepository.save(updateArtist);
    return returnArtistDTO(updateArtist);
  }

  @Transactional
  @Override
  public ArtistDTO removeEntity(Long id) {
    Artist deleteArtist = findArtist(id);
    for (Album a : deleteArtist.getArtistAlbumList()) {
      albumRepository.delete(a);
    }

    Genre parentGenre = deleteArtist.getGenre();
    parentGenre.getArtistList().remove(deleteArtist);
    genreRepository.save(parentGenre);

    artistRepository.delete(deleteArtist);
    return returnArtistDTO(deleteArtist);
  }

  private Artist findArtist(Long id) {
    return artistRepository.findById(id).orElseThrow(RuntimeException::new);
  }

  private ArtistDTO returnArtistDTO(Artist artist) {
    return new ArtistDTO(
        artist.getEntityTitle(),
        artist.getDescription(),
        artist.getId(),
        loadArtistAlbums.loadAllChildEntities(artist),
        new SimpleGenreDTO(artist.getGenre().getEntityTitle()),
        artist.getImage());
  }
}
