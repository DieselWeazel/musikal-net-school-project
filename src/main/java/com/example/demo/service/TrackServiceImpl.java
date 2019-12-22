package com.example.demo.service;

import com.example.demo.model.Album;
import com.example.demo.model.Track;
import com.example.demo.model.dto.TrackDTO;
import com.example.demo.model.dto.simple.*;
import com.example.demo.repositories.TrackRepository;
import com.example.demo.service.childentityservice.LoadChildEntities;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackServiceImpl implements AbstractMusicService<Track, TrackDTO> {

    private final TrackRepository trackRepository;
    private final LoadChildEntities<Album, SimpleTrackDTO> loadTracksFromAlbum;

    public TrackServiceImpl(TrackRepository trackRepository, LoadChildEntities<Album, SimpleTrackDTO> loadTracksFromAlbum) {
        this.trackRepository = trackRepository;
        this.loadTracksFromAlbum = loadTracksFromAlbum;
    }

    @Override
    public List<TrackDTO> loadAll() {
        return trackRepository.findAll().stream().map(this::returnTrackDTO).collect(Collectors.toList());
    }

    @Override
    public TrackDTO findEntity(Track track) {
        Track findTrack = trackRepository.findById(track.getId()).orElseThrow(RuntimeException::new);
        if (findTrack != null) {
            return returnTrackDTO(track);
        } else {
            return null;
        }
    }
    @Transactional
    @Override
    public TrackDTO createEntity(Track track) {
        trackRepository.save(track);
        return returnTrackDTO(track);
    }
    @Transactional
    @Override
    public TrackDTO updateEntity(Long id, Track track) {
        Track updateTrack = findTrack(id);
        updateTrack.setEntityTitle(track.getEntityTitle());
        updateTrack.setDescription(track.getDescription());
        updateTrack.setAlbum(track.getAlbum());
        updateTrack.setArtist(track.getArtist());
        updateTrack.setGenre(track.getGenre());
        trackRepository.save(updateTrack);
        return returnTrackDTO(updateTrack);
    }
    @Transactional
    @Override
    public TrackDTO removeEntity(Long id) {
        Track deleteTrack = trackRepository.getTrackById(id);
        trackRepository.delete(deleteTrack);
        return returnTrackDTO(deleteTrack);
    }

    private Track findTrack(Long id) {
        return trackRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    private TrackDTO returnTrackDTO(Track track) {
        return new TrackDTO(track.getEntityTitle(), track.getDescription(), new SimpleArtistOnlyDTO(track.getArtist().getEntityTitle()),
                new SimpleAlbumOnlyDTO(track.getAlbum().getEntityTitle()), new SimpleGenreDTO(track.getGenre().getEntityTitle()));
    }

}
