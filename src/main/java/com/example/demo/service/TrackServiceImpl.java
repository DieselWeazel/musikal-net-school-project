package com.example.demo.service;

import com.example.demo.model.Album;
import com.example.demo.model.Artist;
import com.example.demo.model.Genre;
import com.example.demo.model.Track;
import com.example.demo.model.dto.TrackDTO;
import com.example.demo.model.dto.create.TrackCreateDTO;
import com.example.demo.model.dto.simple.SimpleAlbumOnlyDTO;
import com.example.demo.model.dto.simple.SimpleArtistOnlyDTO;
import com.example.demo.model.dto.simple.SimpleGenreDTO;
import com.example.demo.repositories.AlbumRepository;
import com.example.demo.repositories.ArtistRepository;
import com.example.demo.repositories.GenreRepository;
import com.example.demo.repositories.TrackRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TrackServiceImpl implements AbstractMusicService<Track, TrackDTO, TrackCreateDTO> {

  private final TrackRepository trackRepository;
  private final GenreRepository genreRepository;
  private final ArtistRepository artistRepository;
  private final AlbumRepository albumRepository;

  public TrackServiceImpl(
      TrackRepository trackRepository,
      GenreRepository genreRepository,
      ArtistRepository artistRepository,
      AlbumRepository albumRepository) {
    this.trackRepository = trackRepository;
    this.genreRepository = genreRepository;
    this.artistRepository = artistRepository;
    this.albumRepository = albumRepository;
  }

  @Override
  public List<TrackDTO> loadAll() {
    return trackRepository.findAll().stream()
        .map(this::returnTrackDTO)
        .collect(Collectors.toList());
  }

  @Override
  public List<TrackDTO> laodByFilter(String filterValue) {
    return trackRepository.findByEntityTitleContainingIgnoreCase(filterValue).stream()
        .map(this::returnTrackDTO)
        .collect(Collectors.toList());
  }

  @Override
  public List<TrackDTO> loadByGenre(String genre) {
    return trackRepository.findAll().stream()
        .map(this::returnTrackDTO)
        .filter(tack -> tack.getGenre().getGenre().equalsIgnoreCase(genre))
        .collect(Collectors.toList());
  }

  @Override
  public TrackDTO findEntity(Track track) {
    Track findTrack = trackRepository.findById(track.getId()).orElseThrow(RuntimeException::new);
    if (findTrack != null) {
      return returnTrackDTO(findTrack);
    } else {
      return null;
    }
  }

  @Transactional
  @Override
  public TrackDTO createEntity(TrackCreateDTO trackCreateDTO) {
    Genre genre =
        genreRepository.findById(trackCreateDTO.getGenreId()).orElseThrow(RuntimeException::new);
    Artist artist =
        artistRepository.findById(trackCreateDTO.getArtistId()).orElseThrow(RuntimeException::new);
    Album album =
        albumRepository.findById(trackCreateDTO.getArtistId()).orElseThrow(RuntimeException::new);
    log.info("Recieved ={}", trackCreateDTO.toString());

    Track newTrack =
        new Track(trackCreateDTO.getName(), trackCreateDTO.getDescription(), genre, album, artist);
    trackRepository.save(newTrack);
    return returnTrackDTO(newTrack);
  }

  @Transactional
  @Override
  public TrackDTO updateEntity(Long id, TrackCreateDTO trackCreateDTO) {
    Track updateTrack = findTrack(id);
    //        Genre genre =
    // genreRepository.findById(trackCreateDTO.getGenreId()).orElseThrow(RuntimeException::new);
    //        Artist artist =
    // artistRepository.findById(trackCreateDTO.getArtistId()).orElseThrow(RuntimeException::new);
    //        Album album =
    // albumRepository.findById(trackCreateDTO.getArtistId()).orElseThrow(RuntimeException::new);

    updateTrack.setEntityTitle(trackCreateDTO.getName());
    updateTrack.setDescription(trackCreateDTO.getDescription());
    trackRepository.save(updateTrack);
    //        updateTrack.setEntityTitle(track.getEntityTitle());
    //        updateTrack.setDescription(track.getDescription());
    //        updateTrack.setAlbum(track.getAlbum());
    //        updateTrack.setArtist(track.getArtist());
    //        updateTrack.setGenre(track.getGenre());
    //        trackRepository.save(updateTrack);
    //        return returnTrackDTO(updateTrack);
    return returnTrackDTO(updateTrack);
  }

  @Transactional
  @Override
  public TrackDTO removeEntity(Long id) {
    Track deleteTrack = findTrack(id);

    // Removes Track from the Genre
    Genre genre = deleteTrack.getGenre();
    genre.getTrackList().remove(deleteTrack);
    genreRepository.save(genre);

    // Removes track from the Artist
    Artist artist = deleteTrack.getArtist();
    artist.getArtistTrackList().remove(deleteTrack);
    artistRepository.save(artist);

    // Removes track from the Album
    Album album = deleteTrack.getAlbum();
    album.getTrackList().remove(deleteTrack);
    albumRepository.save(album);

    // Removes track from repo
    trackRepository.delete(deleteTrack);
    return returnTrackDTO(deleteTrack);
  }

  private Track findTrack(Long id) {
    return trackRepository.findById(id).orElseThrow(RuntimeException::new);
  }

  private TrackDTO returnTrackDTO(Track track) {
    return new TrackDTO(
        track.getEntityTitle(),
        track.getDescription(),
        track.getId(),
        new SimpleArtistOnlyDTO(track.getArtist().getEntityTitle()),
        new SimpleAlbumOnlyDTO(track.getAlbum().getEntityTitle()),
        new SimpleGenreDTO(track.getGenre().getEntityTitle()));
  }
}
