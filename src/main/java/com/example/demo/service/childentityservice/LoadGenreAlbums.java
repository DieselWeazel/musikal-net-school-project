package com.example.demo.service.childentityservice;

import com.example.demo.model.Genre;
import com.example.demo.model.dto.simple.SimpleAlbumOnlyDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class LoadGenreAlbums implements LoadChildEntities<Genre, SimpleAlbumOnlyDTO> {
  @Override
  public List<SimpleAlbumOnlyDTO> loadAllChildEntities(Genre genre) {
    return genre.getAlbumList().stream()
        .map(album -> new SimpleAlbumOnlyDTO(album.getEntityTitle()))
        .collect(Collectors.toList());
  }
}
