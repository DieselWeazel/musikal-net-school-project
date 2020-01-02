package com.example.demo.service;

import com.example.demo.model.Artist;
import com.example.demo.model.Genre;
import com.example.demo.model.Track;
import com.example.demo.model.dto.simple.SimpleTrackDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class AlbumAddTrackService {

  public AlbumAddTrackService() {}

  public List<Track> getTracksForAlbum(List<SimpleTrackDTO> trackList, Genre genre, Artist artist) {
    return trackList.stream()
        .map(
            track ->
                new Track(
                    track.getTrack(),
                    createCorrectNumeration(trackList.indexOf(track)),
                    genre,
                    artist))
        .collect(Collectors.toList());
  }

  private String createCorrectNumeration(int index) {
    return (index < 9) ? "Track 0" + (index + 1) : "Track " + (index + 1);
  }
}
