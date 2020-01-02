package com.example.demo.service.childentityservice;

import com.example.demo.model.Album;
import com.example.demo.model.dto.simple.SimpleTrackDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class LoadAlbumTracks implements LoadChildEntities<Album, SimpleTrackDTO> {
  @Override
  public List<SimpleTrackDTO> loadAllChildEntities(Album album) {
    return album.getTrackList().stream()
        .map(track -> new SimpleTrackDTO(track.getEntityTitle()))
        .collect(Collectors.toList());
  }
}
