package com.example.demo.service.childentityservice;

import com.example.demo.model.Genre;
import com.example.demo.model.dto.simple.SimpleTrackDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class LoadGenreTracks implements LoadChildEntities<Genre, SimpleTrackDTO> {
  @Override
  public List<SimpleTrackDTO> loadAllChildEntities(Genre genre) {
    return genre.getTrackList().stream()
        .map(track -> new SimpleTrackDTO(track.getEntityTitle()))
        .collect(Collectors.toList());
  }
}
